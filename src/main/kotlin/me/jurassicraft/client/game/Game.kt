package me.jurassicraft.client.game

import me.jurassicraft.client.game.render.Renderer
import me.jurassicraft.client.game.resource.AssetManager
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class Game(gameOptions: GameOptions) {

    val window = GameWindow("JurassiCraft", gameOptions)

    val assetManager = AssetManager()

    val renderer = Renderer(this)

    var running = false
        private set

    fun run() {
        log.info { "Engine is running" }
        running = true

        var deltaUpdate = 0f
        var deltaFps = 0f

        var lastAttempt = System.currentTimeMillis()
        var updateTime = lastAttempt
        while (running && !window.closed) {
            window.pollEvents()

            val millisPerUpdate = 1000f / window.options.ups
            val millisPerFrame = if (window.options.fps <= 0) 0f else 1000f / window.options.fps

            val now = System.currentTimeMillis()
            val deltaTime = now - lastAttempt
            deltaUpdate += deltaTime / millisPerUpdate
            deltaFps += deltaTime / millisPerFrame

            if (deltaUpdate >= 1) {
                val diffTimeMillis = now - updateTime
                // update game
                updateTime = now
                deltaUpdate--
            }

            if (window.options.fps <= 0 || deltaFps >= 1) {
                window.refresh()
                renderer.render()
                deltaFps--
            }

            lastAttempt = now
        }

        log.info { "Engine has stopped" }
    }
}