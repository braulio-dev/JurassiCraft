package me.jurassicraft.client.common.element

import me.jurassicraft.client.common.world.World

interface Coordinate {
    val x: Number
    val y: Number
    val z: Number
}

interface WorldPosition : Coordinate {
    var world: World
}

class Position(
    override var x: Int,
    override var y: Int,
    override var z: Int,
    override var world: World
) : WorldPosition {
    constructor(world: World) : this(0, 0, 0, world)
}

class FinePosition(
    override var x: Float,
    override var y: Float,
    override var z: Float,
    override var world: World
) : WorldPosition {
    constructor(world: World) : this(0f, 0f, 0f, world)
}