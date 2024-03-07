package me.jurassicraft.client.game.resource

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL30.glGenerateMipmap
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack.stackPush
import java.io.File

class Texture(override val path: String, override val namespace: String) : Resource {

    val id: Int
    val file: File = File(System.getProperty("user.dir"), "assets/$namespace/textures/$path")
    val width: Int
    val height: Int

    constructor(path: String) : this(path, "jurassicraft")

    init {
        stackPush().use { stack ->
            val w = stack.mallocInt(1)
            val h = stack.mallocInt(1)
            val comp = stack.mallocInt(1)

            val image = stbi_load(file.absolutePath, w, h, comp, 0)
                ?: error("Failed to load image: ${stbi_failure_reason()}")

            val format = when (comp.get(0)) {
                3 -> GL_RGB
                4 -> GL_RGBA
                else -> error("Unsupported image format: $comp")
            }

            id = glGenTextures()
            glBindTexture(GL_TEXTURE_2D, id)
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
            glTexImage2D(GL_TEXTURE_2D, 0, format, w.get(0), h.get(0), 0, format, GL_UNSIGNED_BYTE, image)
            glGenerateMipmap(GL_TEXTURE_2D)

            stbi_image_free(image)

            height = h.get()
            width = w.get()
        }
    }

}