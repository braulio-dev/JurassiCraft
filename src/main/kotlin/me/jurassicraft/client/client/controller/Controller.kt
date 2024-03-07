package me.jurassicraft.client.client.controller

import me.jurassicraft.client.client.Client
import me.jurassicraft.client.client.render.Window

/**
 * Represents a controller for the game engine.
 */
interface Controller {

    /**
     * Takes input from the user.
     */
    fun takeInput(game: Client, window: Window, timeOffset: Long)

    /**
     * Updates the controller.
     */
    fun tick(game: Client, window: Window, timeOffset: Long)

    /**
     * Initializes the controller.
     */
    fun initialize(game: Client, window: Window)

}