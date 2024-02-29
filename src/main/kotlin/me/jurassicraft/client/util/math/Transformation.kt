package me.jurassicraft.client.util.math

import org.joml.Matrix4f
import org.joml.Vector3f

fun getProjection(fov: Float, aspectRatio: Float, zNear: Float, zFar: Float): Matrix4f {
    return Matrix4f().identity().setPerspective(fov, aspectRatio, zNear, zFar)
}

fun getWorldMatrix(offset: Vector3f, rotation: Vector3f, scale: Float): Matrix4f {
    return Matrix4f().identity().translate(offset)
        .rotateX(Math.toRadians(rotation.x.toDouble()).toFloat())
        .rotateY(Math.toRadians(rotation.y.toDouble()).toFloat())
        .rotateZ(Math.toRadians(rotation.z.toDouble()).toFloat())
        .scale(scale)
}