package me.jurassicraft.client.event

/**
 * Represents a keyboard input event.
 */
class KeyboardInputEvent(val key: Int, val mods: Int) : Event() {

    fun isShiftDown() = mods and 1 != 0

    fun isControlDown() = mods and 2 != 0

}