package me.jurassicraft.client.game.render

const val FOV = 60.0f // Deg
const val Z_NEAR = 0.01f
const val Z_FAR = 1000.0f

/**
 * Represents a view that can be rendered to.
 */
data class RenderView(val renderer: GameRenderer, var fov: Float = FOV, var zNear: Float = Z_NEAR, var zFar: Float = Z_FAR) {
    val aspectRatio: Float
        get() = renderer.game.window.aspectRatio
}