package me.jurassicraft.client.game.resource

import mu.KotlinLogging
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Files


private val log = KotlinLogging.logger {}

class AssetManager {

    private val textures = mutableMapOf<String, Texture>()

    init {
        // Copy the asset folder to the working directory
        val url = AssetManager::class.java.classLoader.getResource("assets")
        val existing = File(System.getProperty("user.dir"), "assets")
        FileUtils.deleteDirectory(existing)
        FileUtils.copyDirectoryToDirectory(File(url!!.toURI()), existing.parentFile)
    }

    fun getTexture(path: String): Texture {
        return textures.getOrPut(path) {
            val texture = Texture(path)
            log.info { "Loaded texture: $path" }
            return texture
        }
    }

}