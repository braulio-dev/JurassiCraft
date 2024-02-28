package me.jurassicraft.client.world

import me.jurassicraft.client.element.Position
import me.jurassicraft.client.element.RenderElement
import me.jurassicraft.client.element.impl.Cube

class Chunk(val x: Int, val z: Int, val world: World) : RenderElement {

    private val blocks = mutableListOf<Block>()

    companion object {
        val SIZE = 16
    }

    fun generate() {
        // Check if the chunk is already generated
        if (blocks.isNotEmpty()) {
            throw IllegalStateException("Chunk already generated")
        }

        // Generate the chunk
        TODO()
    }

    override fun render() {
        blocks.forEach { it.render() }
    }
}