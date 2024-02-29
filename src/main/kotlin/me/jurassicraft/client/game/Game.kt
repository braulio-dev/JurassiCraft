package me.jurassicraft.client.game

import me.jurassicraft.client.game.render.GameRenderer
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class Game(windowOptions: WindowOptions) {

    val window = GameWindow("JurassiCraft", windowOptions)

    val renderer = GameRenderer(this)

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