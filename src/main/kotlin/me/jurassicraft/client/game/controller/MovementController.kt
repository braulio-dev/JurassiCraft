package me.jurassicraft.client.game.controller

import me.jurassicraft.client.game.Game
import me.jurassicraft.client.game.model.Controller
import me.jurassicraft.client.game.view.Window
import me.jurassicraft.client.util.Keys
import me.jurassicraft.client.util.isKeyDown

class MovementController : Controller {

    override fun takeInput(game: Game, window: Window, timeOffset: Long) {
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

    override fun tick(game: Game, window: Window, timeOffset: Long) = Unit

    override fun initialize(game: Game, window: Window) = Unit

}