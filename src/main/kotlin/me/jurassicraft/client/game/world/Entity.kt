package me.jurassicraft.client.game.world

import me.jurassicraft.client.world.World
import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f

private var idCount = 0

class Entity(world: World) {

    val id = ++idCount

    internal val worldMatrix = Matrix4f()
    internal var scale: Float = 1.0f
    internal var position: Vector3f = Vector3f()
    internal var rotation: Quaternionf = Quaternionf()

    // radians
    fun setRotation(x: Float, y: Float, z: Float, angle: Float) {
        rotation.fromAxisAngleRad(x, y, z, angle)
    }

    internal fun update() {
        worldMatrix.translationRotateScale(position, rotation, scale)
    }

}