package me.jurassicraft.client.game.render

import me.jurassicraft.client.Game
import mu.KotlinLogging
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL20.*

private val log = KotlinLogging.logger { }

class Renderer(internal val game: Game) {

    private val shaderProgram: ShaderProgram
    private val view = RenderView(this)
    private val models = mutableListOf<Model>()

    init {
        createCapabilities()
        shaderProgram = ShaderProgram().apply {
            createVertexShader("/shader/vertex.vs")
            createFragmentShader("/shader/fragment.fs")
            link()
        }

        shaderProgram.createUniform("world")
        shaderProgram.createUniform("projection")
        shaderProgram.createUniform("tex_sampler")
        view.update()
    }

    internal fun destroy() {
        shaderProgram.destroy()
        models.forEach { it.destroy() }
    }

    internal fun render() {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        glViewport(0, 0, game.window.dimension.first, game.window.dimension.second)

        // Use the shader program
        shaderProgram.bind()
        shaderProgram.setUniform("projection", view.projectionMatrix)
        shaderProgram.setUniform("tex_sampler", 0)

        renderQueue()

        // Unbind the shader program
        shaderProgram.unbind()
    }

    // Render all elements in the render queue
    private fun renderQueue() = models.listIterator().apply {
        while (hasNext()) {
            val model = next()
            model.modelParts.forEach { mesh ->
                glActiveTexture(GL_TEXTURE0)
                mesh.bind()
                glEnable(GL_DEPTH_TEST)
                model.trackedEntities.forEach { entity ->
                    shaderProgram.setUniform("world", entity.worldMatrix)
                    glDrawElements(GL_TRIANGLES, mesh.vertexCount, GL_UNSIGNED_INT, 0)
                }
                glDisable(GL_DEPTH_TEST)
                mesh.unbind()
            }
        }
    }

    internal fun addModel(element: Model) {
        models.add(element)
    }

}