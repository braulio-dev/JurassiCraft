package me.jurassicraft.client.element

import me.jurassicraft.client.world.World

interface Coordinate {
    val x: Number
    val y: Number
    val z: Number
}

interface WorldPosition : Coordinate {
    val world: World
}

class Position(
    override val x: Int,
    override val y: Int,
    override val z: Int,
    override val world: World
) : WorldPosition

class FinePosition(
    override val x: Float,
    override val y: Float,
    override val z: Float,
    override val world: World
) : WorldPosition