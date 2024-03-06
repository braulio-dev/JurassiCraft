package me.jurassicraft.client

import me.jurassicraft.client.game.GameWindow
import me.jurassicraft.client.game.WindowOptions
import me.jurassicraft.client.game.render.Renderer
import me.jurassicraft.client.game.resource.AssetManager
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class Game(windowOptions: WindowOptions) {

    val window = GameWindow("JurassiCraft", windowOptions)

    val assetManager = AssetManager()

    val renderer = Renderer(this)

    var running = false
        private set

    fun run() {
        log.info { "Engine is running" }
        running = true

        var renderTime = System.currentTimeMillis()

        while (running && !window.closed) {
            window.pollEvents()

            val currentTime = System.currentTimeMillis()
            renderer.render()
            window.refresh()
            renderTime = currentTime
        }

        log.info { "Engine has stopped" }
    }

}