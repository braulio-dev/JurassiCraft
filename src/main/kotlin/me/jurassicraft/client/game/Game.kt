package me.jurassicraft.client.game

import me.jurassicraft.client.world.World
import org.joml.Matrix4f
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.glfw.GLFWScrollCallback
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL20.*

class Game(private val windowSize: Pair<Int, Int> = Pair(1020, 720)) {

    private var errorCallback : GLFWErrorCallback? = null
    private var keyCallback : GLFWKeyCallback? = null
    private var cursorPosCallback : GLFWCursorPosCallback? = null
    private var scrollCallback : GLFWScrollCallback? = null
    private var window: Long? = null
    private var world: World = World("JurassiCraft", 100, this)
    private var camera: Camera = Camera()

    private val TARGET_UPS = 50
    private val TIME_STEP = 1f / TARGET_UPS
    private var lastFrameTime: Float = 0f
    private var deltaFrameTime: Float = 0f

    fun start() {
        try {
            init()
            tick()
            glfwDestroyWindow(window!!)
            keyCallback?.free()
            cursorPosCallback?.free()
        } finally {
            glfwTerminate()
            errorCallback?.free()
        }
    }

    private fun init() {
        errorCallback = glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))
        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        // Configure GLFW
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
        glEnable(GL_DEPTH_TEST)

        glfwSetInputMode(window!!, GLFW_CURSOR, GLFW_CURSOR_DISABLED)

        // Create the window
        window = glfwCreateWindow(windowSize.first, windowSize.second, "JurassiCraft", 0, 0)

        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())!!
        glfwSetWindowPos(window!!, (vidmode.width() - windowSize.first) / 2, (vidmode.height() - windowSize.second) / 2)

        // Set the key callback
        keyCallback = glfwSetKeyCallback(window!!, object : GLFWKeyCallback() {
            override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
                // Close the window if the user presses the escape key
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, true)
                } else {
                    camera.processKeyInput(window, deltaFrameTime)
                }
            }
        })

        cursorPosCallback = glfwSetCursorPosCallback(window!!) { _, xpos, ypos ->
            camera.processMouseInput(xpos.toFloat(), ypos.toFloat())
        }

        scrollCallback = glfwSetScrollCallback(window!!) { _, xOffset, yOffset ->
            camera.processScrollInput(yOffset.toFloat())
        }


        // Change viewport so that it matches the window size
        // Also set a callback to change the viewport when the window is resized
//        glViewport(0, 0, windowSize.first, windowSize.second)
//        glfwSetFramebufferSizeCallback(window!!) { _, width, height -> glViewport(0, 0, width, height) }

        // Make the OpenGL context current and show the window
        glfwMakeContextCurrent(window!!)
        glfwSwapInterval(1)
        glfwShowWindow(window!!)
        createCapabilities()
    }

    private fun tick() {
        if (glfwWindowShouldClose(window!!)) {
            return
        }

        glClearColor(1.0f, 1.0f, 1.0f, 0.0f)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        world.render()

        // Update the screen
        glfwSwapBuffers(window!!)
        glfwPollEvents()

        deltaFrameTime = (glfwGetTime().toFloat() - lastFrameTime)
        lastFrameTime = glfwGetTime().toFloat()
        tick() // Recursive call to loop
    }

}