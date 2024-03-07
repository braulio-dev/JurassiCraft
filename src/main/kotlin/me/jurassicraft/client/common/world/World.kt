package me.jurassicraft.client.common.world

class World(val name: String, val height: Int) {

    private val chunks = mutableListOf<Chunk>()

    fun render() {
        println("Rendering world")
    }

}