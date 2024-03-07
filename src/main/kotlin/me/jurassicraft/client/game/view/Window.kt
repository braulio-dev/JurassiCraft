package me.jurassicraft.client.game.view

import me.jurassicraft.client.event.callEvent
import me.jurassicraft.client.event.KeyboardInputEvent
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*

private val log = mu.KotlinLogging.logger { }

data class Options(
    val compatibleProfile: Boolean,
    val fps: Int,
    val tps: Int,
    val dimension: Pair<Int, Int>
)

class Window(title: String, val options: Options) {

    val id: Long

    var dimension: Pair<Int, Int>

    val aspectRatio: Float
        get() = dimension.first.toFloat() / dimension.second.toFloat()

    val closed: Boolean
        get() = glfwWindowShouldClose(id)

    init {
        if (options.dimension.first <= 0 || options.dimension.second <= 0) {
            error("Invalid window dimensions")
        } else if (!glfwInit()) {
            error("Unable to initialize GLFW")
        }
        dimension = options.dimension

        // Configure GLFW
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        if (options.compatibleProfile) {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE)
        } else {
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
        }

        // Create the window
        id = glfwCreateWindow(dimension.first, dimension.second, title, 0, 0)

        // Center the window on the screen
        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())!!
        glfwSetWindowPos(id, (vidmode.width() - dimension.first) / 2, (vidmode.height() - dimension.second) / 2)

        // Setup callbacks
        glfwSetFramebufferSizeCallback(id, ::onResize)
        glfwSetErrorCallback(::onError)
        glfwSetKeyCallback(id, ::onInput)

        // Make the OpenGL context current and show the window
        glfwMakeContextCurrent(id)
        glfwSwapInterval(if (options.fps > 0) 1 else 0)
        glfwShowWindow(id)
    }

    fun destroy() {
        glfwFreeCallbacks(id)
        glfwDestroyWindow(id)
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
        glfwSetKeyCallback(id, null)?.free()
        glfwSetFramebufferSizeCallback(id, null)?.free()
    }

    fun pollEvents() {
        glfwPollEvents()
    }

    fun refresh() {
        glfwSwapBuffers(id)
    }

    private fun onError(code: Int, msg: Long) {
        log.error { "GLFW Error ($code): $msg" }
    }

    private fun onResize(window: Long, width: Int, height: Int) {
        dimension = Pair(width, height)
    }

    private fun onInput(window: Long, key: Int, scanCode: Int, action: Int, mods: Int) {
        // Close the window if the user presses the escape key
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(window, true)
        }

        callEvent(KeyboardInputEvent(key, mods))
    }

}
