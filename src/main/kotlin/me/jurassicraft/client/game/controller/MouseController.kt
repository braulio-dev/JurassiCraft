package me.jurassicraft.client.game.controller

import me.jurassicraft.client.game.Game
import me.jurassicraft.client.game.model.Controller
import me.jurassicraft.client.game.view.Window
import org.joml.Vector2f
import org.lwjgl.glfw.GLFW.*

class MouseController : Controller {

    private var lastPos: Vector2f = Vector2f()
    val currentPos: Vector2f = Vector2f()
    var leftButtonDown: Boolean = false
    var rightButtonDown: Boolean = false
    var hovered: Boolean = false
        private set

    override fun initialize(game: Game, window: Window) {
        // Disable cursor inside
        glfwSetInputMode(window.id, GLFW_CURSOR, GLFW_CURSOR_DISABLED)

        // Update hover state
        glfwSetCursorEnterCallback(window.id) { _, entered ->
            hovered = entered
        }

        // Update x, y position of the mouse when it moves
        glfwSetCursorPosCallback(window.id) { _, x, y ->
            currentPos.x = x.toFloat()
            currentPos.y = y.toFloat()
        }

        // Update click state
        glfwSetMouseButtonCallback(window.id) { _, button, action, _ ->
            rightButtonDown = action == GLFW_PRESS && button == GLFW_MOUSE_BUTTON_RIGHT
            leftButtonDown = action == GLFW_PRESS && button == GLFW_MOUSE_BUTTON_LEFT
        }
    }

    override fun takeInput(game: Game, window: Window, timeOffset: Long) {
        val movement = Vector2f()

        // Update if we're in window
        if (hovered && lastPos.x > 0f && lastPos.y > 0f) {
            val xMov = currentPos.x - lastPos.x
            val yMov = currentPos.y - lastPos.y

            // invertido
            if (xMov != 0f) movement.y = xMov
            if (yMov != 0f) movement.x = yMov
        }

        lastPos.y = currentPos.y
        lastPos.x = currentPos.x

        // Move around if there is a distance to move, otherwise returb
        if (movement.x == 0f && movement.y == 0f) {
            return
        }

        val sensitivity = 0.07f
        movement.mul(sensitivity) // Multiply times sensitivity
        game.camera.rotate(
            Math.toRadians(movement.x.toDouble()).toFloat(),
            Math.toRadians(movement.y.toDouble()).toFloat(),
        )
    }

    override fun update(game: Game, window: Window, timeOffset: Long) = Unit

}