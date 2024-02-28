package me.jurassicraft.client.world

import me.jurassicraft.client.element.RenderElement
import me.jurassicraft.client.game.Game
import org.lwjgl.glfw.GLFW.glfwMakeContextCurrent
import org.lwjgl.opengl.GL11.*

class World(val name: String, val height: Int, val context: Game) : RenderElement {

    private val chunks = mutableListOf<Chunk>()

    override fun render() {
//        chunks.forEach { it.render() }


        // TODO: Implement rendering
        // Render square top left
        glColor3f(0.0f, 1.0f, 0.0f)
        glBegin(GL_QUADS)
        glVertex2f(0.0f, 0.0f)
        glVertex2f(0.5f, 0.0f)
        glVertex2f(0.5f, 0.5f)
        glVertex2f(0.0f, 0.5f)
        glEnd()
    }
}