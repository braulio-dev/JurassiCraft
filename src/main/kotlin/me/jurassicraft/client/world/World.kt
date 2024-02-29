package me.jurassicraft.client.world

class World(val name: String, val height: Int) {

    private val chunks = mutableListOf<Chunk>()

    fun render() {
        println("Rendering world")
    }

}