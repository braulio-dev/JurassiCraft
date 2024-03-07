package me.jurassicraft.client.client

import me.jurassicraft.client.client.controller.MouseController
import me.jurassicraft.client.client.controller.MovementController
import me.jurassicraft.client.client.render.Renderer
import me.jurassicraft.client.client.asset.AssetManager
import me.jurassicraft.client.client.render.Camera
import me.jurassicraft.client.client.render.Options
import me.jurassicraft.client.client.render.Window
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class Client(gameOptions: Options) {

    val controllers = mutableListOf(MovementController(), MouseController())
    val window = Window("JurassiCraft", gameOptions)
    val assetManager = AssetManager()
    val renderer = Renderer(this)
    val camera = Camera()
    var running = false
        private set

    init {
        log.info { "Initializing client..." }
        log.info { "Loading controllers..." }
        controllers.forEach { it.initialize(this, window) }
        log.info { "Client has been initialized" }
    }

    fun run() {
        log.info { "Client is running" }
        running = true

        var deltaTicks = 0f
        var deltaFps = 0f

        var lastAttempt = System.currentTimeMillis()
        var tickTime = lastAttempt
        while (running && !window.closed) {
            window.pollEvents()

            val millisPerTick = 1000f / window.options.tps
            val millisPerFrame = if (window.options.fps <= 0) 0f else 1000f / window.options.fps

            val now = System.currentTimeMillis()
            val deltaTime = now - lastAttempt
            deltaTicks += deltaTime / millisPerTick
            deltaFps += deltaTime / millisPerFrame

            if (deltaTicks >= 1 || window.options.fps <= 0) {
                controllers.forEach { it.takeInput(this, window, deltaTime) }
            }

            if (deltaTicks >= 1) {
                controllers.forEach { it.tick(this, window, now - tickTime) }
                tickTime = now
                deltaTicks--
            }

            if (window.options.fps <= 0 || deltaFps >= 1) {
                window.refresh()
                renderer.render()
                deltaFps--
            }

            lastAttempt = now
        }

        log.info { "Client has stopped" }
    }
}