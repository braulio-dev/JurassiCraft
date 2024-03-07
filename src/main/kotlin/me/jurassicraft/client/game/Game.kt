package me.jurassicraft.client.game

import me.jurassicraft.client.game.controller.MouseController
import me.jurassicraft.client.game.controller.MovementController
import me.jurassicraft.client.game.model.Controller
import me.jurassicraft.client.game.render.Renderer
import me.jurassicraft.client.game.resource.AssetManager
import me.jurassicraft.client.game.view.Camera
import me.jurassicraft.client.game.view.Options
import me.jurassicraft.client.game.view.Window
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class Game(gameOptions: Options) {

    val controllers = mutableListOf(MovementController(), MouseController())
    val window = Window("JurassiCraft", gameOptions)
    val assetManager = AssetManager()
    val renderer = Renderer(this)
    val camera = Camera()
    var running = false
        private set

    init {
        log.info { "Initializing engine..." }
        log.info { "Loading controllers..." }
        controllers.forEach { it.initialize(this, window) }
        log.info { "Engine has been initialized" }
    }

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

            if (deltaUpdate >= 1 || window.options.fps <= 0) {
                controllers.forEach { it.takeInput(this, window, deltaTime) }
            }

            if (deltaUpdate >= 1) {
                controllers.forEach { it.update(this, window, now - updateTime) }
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