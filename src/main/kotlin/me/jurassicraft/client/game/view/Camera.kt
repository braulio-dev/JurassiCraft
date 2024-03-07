package me.jurassicraft.client.game.view

import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import kotlin.math.cos
import kotlin.math.sin

class Camera {

    val position = Vector3f()
    val rotation = Vector2f()
    var viewMatrix = Matrix4f()
        private set

    init {
        update()
    }

    private fun update() {
        viewMatrix = Matrix4f().identity()
            .rotateX(rotation.x)
            .rotateY(rotation.y)
            .translate(-position.x, -position.y, -position.z);
    }

    fun rotate(yaw: Float, pitch: Float) {
        rotation.add(yaw, pitch)
        update()
    }

    fun move(x: Float, y: Float, z: Float) {
        // We'll be moving the view matrix in the negative direction of X, Y, Z
        // and the absolute position of the camera will go in the right direction
        val movement = Vector3f(x, y, z)
        val length = movement.length()

        // Hacemos el movimiento cero antes de cada modificación para no tomar en cuenta
        // Previos cálculos antes de hacer update cada vez. También normalizamos el
        // tamaño del vector de movimiento al tamaño del vector original para no perder
        // velocidad, ya que nos deshacemos del movimiento en Y del movimiento lateral
        // y frontal para no interferir con el movimiento en 'y'

        // Up-down
        if (y != 0f) {
            position.add(0f, y, 0f)
            update()
        }

        // Right-left
        if (x != 0f) {
            movement.zero()
            // Move
            if (x < 0) viewMatrix.positiveX(movement).mul(-x)
            else viewMatrix.positiveX(movement).mul(x)
            // Remove vertical movement
            movement.y = 0f
            if (x < 0) position.sub(movement.normalize(length))
            else position.add(movement.normalize(length))
            update()
        }

        // Front-back
        if (z != 0f) {
            movement.zero()
            // Move
            if (z < 0) viewMatrix.positiveZ(movement).mul(-z)
            else viewMatrix.positiveZ(movement).mul(z)
            // Remove vertical movement
            movement.y = 0f
            if (z < 0) position.sub(movement.normalize(length))
            else position.add(movement.normalize(length))
            update()
        }

    }

}