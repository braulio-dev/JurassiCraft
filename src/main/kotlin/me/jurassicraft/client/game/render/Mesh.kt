package me.jurassicraft.client.game.render

import org.lwjgl.opengl.GL30.*

class Mesh(vertices: FloatArray, indices: IntArray, colors: FloatArray? = null) {

    private val vaoId: Int
    private val vboId: Int
    private val iboId: Int
    private val cboId: Int
    val vertexCount: Int = indices.size

    init {
        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        // Create VBO and upload vertex data
        vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)

        // Create IBO and upload index data
        iboId = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW)

        // Create CBO and upload color data
        if (colors != null) {
            cboId = glGenBuffers()
            glBindBuffer(GL_ARRAY_BUFFER, cboId)
            glBufferData(GL_ARRAY_BUFFER, colors, GL_STATIC_DRAW)
            glEnableVertexAttribArray(1)
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0)
        } else {
            cboId = 0
        }

        // Unbind VAO
        glBindVertexArray(0)
    }

    fun bind() {
        glBindVertexArray(vaoId)
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
    }

    fun unbind() {
        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        glBindVertexArray(0)
    }

    fun destroy() {
        glDeleteBuffers(vboId)
        glDeleteBuffers(iboId)
        glDeleteVertexArrays(vaoId)
        if (cboId != 0) glDeleteBuffers(cboId)
    }
}