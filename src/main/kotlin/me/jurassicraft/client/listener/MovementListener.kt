package me.jurassicraft.client.listener

import me.jurassicraft.client.event.EventHandler
import me.jurassicraft.client.event.KeyboardInputEvent

class MovementListener {

    @EventHandler
    fun onMove(event: KeyboardInputEvent) {
        println("Key: ${event.key}, Modifiers: ${event.mods}")
    }

}