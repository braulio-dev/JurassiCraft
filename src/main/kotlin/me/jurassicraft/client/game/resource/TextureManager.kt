package me.jurassicraft.client.game.resource

import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class TextureManager {

    private val textures = mutableMapOf<String, Texture>()

    operator fun get(path: String): Texture {
        return textures.getOrPut(path) {
            val texture = Texture(path)
            log.info { "Loaded texture: $path" }
            return texture
        }
    }

}