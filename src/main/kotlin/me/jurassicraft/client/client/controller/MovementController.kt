package me.jurassicraft.client.client.controller

import me.jurassicraft.client.client.Client
import me.jurassicraft.client.client.render.Window
import me.jurassicraft.client.util.Keys
import me.jurassicraft.client.util.isKeyDown

class MovementController : Controller {

    override fun takeInput(game: Client, window: Window, timeOffset: Long) {
        val moveDelta = 0.002f
        if (isKeyDown(window, Keys.W)) {
            game.camera.move(0f, 0f, -moveDelta)
        }

        if (isKeyDown(window, Keys.S)) {
            game.camera.move(0f, 0f, moveDelta)
        }

        if (isKeyDown(window, Keys.A)) {
            game.camera.move(-moveDelta, 0f, 0f)
        }

        if (isKeyDown(window, Keys.D)) {
            game.camera.move(moveDelta, 0f, 0f)
        }

        if (isKeyDown(window, Keys.SPACE)) {
            game.camera.move(0f, moveDelta, 0f)
        }

        if (isKeyDown(window, Keys.LEFT_SHIFT)) {
            game.camera.move(0f, -moveDelta, 0f)
        }
    }

    override fun tick(game: Client, window: Window, timeOffset: Long) = Unit

    override fun initialize(game: Client, window: Window) = Unit

}