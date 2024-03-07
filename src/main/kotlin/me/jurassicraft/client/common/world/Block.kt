package me.jurassicraft.client.common.world

import me.jurassicraft.client.common.element.Position
import me.jurassicraft.client.common.element.impl.Cube

/**
 * Represents an in-world block with a position and a predefined cube.
 */
class Block(val cube: Cube, val position: Position) {

    fun render() {
        cube.render()
    }
}