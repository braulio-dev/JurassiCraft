package me.jurassicraft.client.world

import me.jurassicraft.client.element.RenderElement
import me.jurassicraft.client.game.render.GameRenderer
import org.lwjgl.opengl.GL11.*

class World(val name: String, val height: Int, val context: GameRenderer) : RenderElement {

    private val chunks = mutableListOf<Chunk>()

    override fun render() {
        println("Rendering world")

        // Front face (red)
        glColor3f(0.0f, 0.0f, 0.0f)
        glEnable(GL_DEPTH_TEST)
        glBegin(GL_QUADS)
        glVertex3f(-0.5f, -0.5f, 0.5f)
        glVertex3f(0.5f, -0.5f, 0.5f)
        glVertex3f(0.5f, 0.5f, 0.5f)
        glVertex3f(-0.5f, 0.5f, 0.5f)
        glEnd()

//        // Back face (green)
        glBegin(GL_QUADS)
        glEnable(GL_DEPTH_TEST)
        glColor3f(0.0f, 1.0f, 0.0f)
        glVertex3f(-0.5f, -0.6f, -0.5f)
        glVertex3f(0.5f, -0.6f, -0.5f)
        glVertex3f(0.5f, 0.6f, -0.5f)
        glVertex3f(-0.5f, 0.6f, -0.5f)
        glEnd()
//
//        // Left face (blue)
//        glColor3f(0.0f, 0.0f, 1.0f)
//        glBegin(GL_QUADS)
//        glVertex3f(-0.5f, -0.5f, -0.5f)
//        glVertex3f(-0.5f, -0.5f, 0.5f)
//        glVertex3f(-0.5f, 0.5f, 0.5f)
//        glVertex3f(-0.5f, 0.5f, -0.5f)
//        glEnd()
//
//        // Right face (yellow)
//        glColor3f(1.0f, 1.0f, 0.0f)
//        glBegin(GL_QUADS)
//        glVertex3f(0.5f, -0.5f, -0.5f)
//        glVertex3f(0.5f, 0.5f, -0.5f)
//        glVertex3f(0.5f, 0.5f, 0.5f)
//        glVertex3f(0.5f, -0.5f, 0.5f)
//        glEnd()
//
//        // Top face (cyan)
//        glColor3f(0.0f, 1.0f, 1.0f)
//        glBegin(GL_QUADS)
//        glVertex3f(-0.5f, 0.5f, -0.5f)
//        glVertex3f(-0.5f, 0.5f, 0.5f)
//        glVertex3f(0.5f, 0.5f, 0.5f)
//        glVertex3f(0.5f, 0.5f, -0.5f)
//        glEnd()
//
//        // Bottom face (magenta)
//        glColor3f(1.0f, 0.0f, 1.0f)
//        glBegin(GL_QUADS)
//        glVertex3f(-0.5f, -0.5f, -0.5f)
//        glVertex3f(0.5f, -0.5f, -0.5f)
//        glVertex3f(0.5f, -0.5f, 0.5f)
//        glVertex3f(-0.5f, -0.5f, 0.5f)
//        glEnd()

        glDisable(GL_DEPTH_TEST)
    }

}