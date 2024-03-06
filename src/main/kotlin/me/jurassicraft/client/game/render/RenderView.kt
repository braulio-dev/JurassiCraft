package me.jurassicraft.client.game.render

import me.jurassicraft.client.util.math.getProjection
import org.joml.Matrix4f

const val FOV = 60f // Deg
const val Z_NEAR = 0.01f
const val Z_FAR = 1000.0f

/**
 * Represents a view that can be rendered to.
 */
data class RenderView(val renderer: Renderer, var fov: Float = FOV, var zNear: Float = Z_NEAR, var zFar: Float = Z_FAR) {

    var projectionMatrix = Matrix4f()
        private set

    val aspectRatio: Float
        get() = renderer.game.window.aspectRatio

    fun update() {
        projectionMatrix = getProjection(Math.toRadians(fov.toDouble()).toFloat(), aspectRatio, zNear, zFar)
    }
}