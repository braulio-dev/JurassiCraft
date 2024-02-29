package me.jurassicraft.client.world

class Chunk(val x: Int, val z: Int, val world: World) {

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

    fun render() {
        blocks.forEach { it.render() }
    }
}