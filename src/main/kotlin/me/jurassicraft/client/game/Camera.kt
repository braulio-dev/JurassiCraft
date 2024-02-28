package me.jurassicraft.client.game

import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*
import kotlin.math.cos
import kotlin.math.sin

class Camera {
    val position = Vector3f(0.0f, 0.0f, 0.0f)
    private val front = Vector3f(0.0f, 0.0f, -1.0f)
    private val up = Vector3f(0.0f, 1.0f, 0.0f)
    private var fov = 45.0f

    var yaw = -90.0f
        private set
    var pitch = 0.0f
        private set
    private val sensitivity = 0.1f

    fun processKeyInput(window: Long, deltaTime: Float) {
        val speed = 5.0f * deltaTime

        // Forward
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS)
            position.add(front.mul(speed))

        // Backward
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS)
            position.sub(front.mul(speed))

        // Left
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS)
            position.sub(front.cross(up, Vector3f()).normalize().mul(speed))

        // Right
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS)
            position.add(front.cross(up, Vector3f()).normalize().mul(speed))

        // Up
        if (glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS)
            position.add(up.mul(speed))
    }

    fun processScrollInput(yOffset: Float) {
        fov -= yOffset
        if (fov < 1.0f)
            fov = 1.0f
        if (fov > 45.0f)
            fov = 45.0f
    }

    fun processMouseInput(xPos: Float, yPos: Float) {
        val xOffset = xPos - position.x
        val yOffset = position.y - yPos

        yaw += xOffset * sensitivity
        pitch += yOffset * sensitivity

        if (pitch > 89.0f)
            pitch = 89.0f
        if (pitch < -89.0f)
            pitch = -89.0f

        updateCameraVectors()
    }

    private fun updateCameraVectors() {
        front.x = cos(Math.toRadians(yaw.toDouble())).toFloat() * cos(Math.toRadians(pitch.toDouble())).toFloat()
        front.y = sin(Math.toRadians(pitch.toDouble())).toFloat()
        front.z = sin(Math.toRadians(yaw.toDouble())).toFloat() * cos(Math.toRadians(pitch.toDouble())).toFloat()
        front.normalize()
    }

    fun getViewMatrix(): Matrix4f {
        return Matrix4f().lookAt(position, position.add(front, Vector3f()), up)
    }
}
