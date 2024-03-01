package me.jurassicraft.client.game.resource

import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.ARBFramebufferObject.glGenerateMipmap
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.nio.ByteBuffer

class Texture(override val path: String, override val namespace: String) : Resource {

    val id: Int

    constructor(path: String) : this(path, "jurassicraft")

    init {
        val decoder = PNGDecoder(Texture::class.java.getResourceAsStream(path))
        val buffer = ByteBuffer.allocateDirect(4 * decoder.width * decoder.height)
        decoder.decode(buffer, decoder.width * 4, PNGDecoder.Format.RGBA)
        buffer.flip()

        id = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, id)
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.width, decoder.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer)
        glGenerateMipmap(GL_TEXTURE_2D)
    }

}