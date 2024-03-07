package me.jurassicraft.client.client.render.model

import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f

class ModeledObject {

    internal val modelMatrix = Matrix4f()
    internal var scale: Float = 1.0f
    internal var position: Vector3f = Vector3f()
    internal var rotation: Quaternionf = Quaternionf()

    init {
        update()
    }

    // radians
    fun setRotation(x: Float, y: Float, z: Float, angle: Float) {
        rotation.fromAxisAngleRad(x, y, z, angle)
        update()
    }

    internal fun update() {
        modelMatrix.translationRotateScale(position, rotation, scale)
    }

}