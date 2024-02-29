package me.jurassicraft.client.world

import me.jurassicraft.client.element.Position
import me.jurassicraft.client.element.impl.Cube

/**
 * Represents an in-world block with a position and a predefined cube.
 */
class Block(val cube: Cube, val position: Position) {

    fun render() {
        cube.render()
    }
}