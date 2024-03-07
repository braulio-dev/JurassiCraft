package me.jurassicraft.client.client.render.model

import org.lwjgl.opengl.GL30.*

class Mesh(vertices: FloatArray, indices: IntArray, private val option: MeshOption? = null) {

    private val vaoId: Int
    private val vboId: Int
    private val iboId: Int
    val vertexCount: Int = indices.size

    init {
        vaoId = glGenVertexArrays()
        glBindVertexArray(vaoId)

        // Create VBO and upload vertex data
        vboId = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
        glEnableVertexAttribArray(0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        // Create CBO and upload color or texture data
        option?.create(this)

        // Create IBO and upload index data
        iboId = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW)

        // Unbind VAO
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)
    }

    fun bind() {
        glBindVertexArray(vaoId)
        glEnableVertexAttribArray(0)
        option?.bind(this)
    }

    fun unbind() {
        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        option?.unbind(this)
    }

    fun destroy() {
        glDeleteBuffers(vboId)
        glDeleteBuffers(iboId)
        glDeleteVertexArrays(vaoId)
        option?.destroy(this)
    }
}