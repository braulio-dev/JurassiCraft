package me.jurassicraft.client.game.render

import me.jurassicraft.client.game.Game
import org.joml.Matrix4f
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL20.*

class GameRenderer(internal val game: Game) {

    private val shaderProgram: ShaderProgram
    private val view = RenderView(this)
    private val projectionMatrix = Matrix4f()

    init {
        createCapabilities()
        shaderProgram = ShaderProgram().apply {
            createVertexShader("/shader/vertex.vs")
            createFragmentShader("/shader/fragment.fs")
            createUniform("projection")
            link()
        }

        projectionMatrix.perspective(view.fov, view.aspectRatio, view.zNear, view.zFar)
    }

    internal fun destroy() {
        shaderProgram.destroy()
    }

    internal fun render() {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        glViewport(0, 0, game.window.dimension.first, game.window.dimension.second)

        // Define the vertices of the triangle
        val positions = floatArrayOf(
            -0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
        )

        val indices = intArrayOf(
            0, 1, 3, 3, 1, 2,
        )

        val colors = floatArrayOf(
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
        )

        // Create a Mesh object and pass the vertices to it
        val mesh = Mesh(positions, indices, colors)

        shaderProgram.bind()
        mesh.bind()

        // Update the projection matrix
        shaderProgram.setUniform("projection", projectionMatrix)

        // Draw the triangle
        glDrawElements(GL_TRIANGLES, mesh.vertexCount, GL_UNSIGNED_INT, 0)

        mesh.unbind()
        shaderProgram.unbind()
    }

}