package me.jurassicraft.client.game.model

import me.jurassicraft.client.game.Game
import me.jurassicraft.client.game.view.Window

/**
 * Represents a controller for the game engine.
 */
interface Controller {

    /**
     * Takes input from the user.
     */
    fun takeInput(game: Game, window: Window, timeOffset: Long)

    /**
     * Updates the controller.
     */
    fun update(game: Game, window: Window, timeOffset: Long)

    /**
     * Initializes the controller.
     */
    fun initialize(game: Game, window: Window)

}