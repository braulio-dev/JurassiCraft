package me.jurassicraft.client.game.render

import me.jurassicraft.client.game.resource.Texture
import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack
import java.nio.FloatBuffer

/**
 * Mesh option to change the color or texture of a mesh.
 */
sealed interface MeshOption {

    fun create(mesh: Mesh)

    fun bind(mesh: Mesh)

    fun unbind(mesh: Mesh)

    fun destroy(mesh: Mesh)

}

/**
 * Changes the color of a mesh.
 */
class ColorOption(private val color: FloatArray) : MeshOption {

    private var cboId: Int = 0

    override fun create(mesh: Mesh) {
        cboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, cboId)
        glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW)
        glEnableVertexAttribArray(1)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    override fun bind(mesh: Mesh) {
        glEnableVertexAttribArray(1)
    }

    override fun unbind(mesh: Mesh) {
        glDisableVertexAttribArray(1)
    }

    override fun destroy(mesh: Mesh) {
        glDeleteBuffers(cboId)
    }
}

class TextureOption(private val texture: Texture, val coords: FloatArray) : MeshOption {

    private var tboId: Int = 0

    override fun create(mesh: Mesh) {
        tboId = glGenBuffers()

        // flip coords
//        for (i in 1 until coords.size step 2) {
//            coords[i] = 1 - coords[i]
//        }

        MemoryStack.stackPush().use {
            val textCoordsBuffer: FloatBuffer = it.callocFloat(coords.size)
            textCoordsBuffer.put(0, coords)

            glEnableVertexAttribArray(1)
            glBindBuffer(GL_ARRAY_BUFFER, tboId)
            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW)
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0)
            glBindBuffer(GL_ARRAY_BUFFER, 0)
        }
    }

    override fun bind(mesh: Mesh) {
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, texture.id)
        glEnableVertexAttribArray(1)
    }

    override fun unbind(mesh: Mesh) {
        glDisableVertexAttribArray(1)
    }

    override fun destroy(mesh: Mesh) {
        glDeleteBuffers(tboId)
        glDeleteTextures(texture.id)
    }

}

