package me.jurassicraft.client

import me.jurassicraft.client.event.EventHandler
import me.jurassicraft.client.event.KeyboardInputEvent
import me.jurassicraft.client.event.classScan
import me.jurassicraft.client.game.WindowOptions
import me.jurassicraft.client.game.render.Mesh
import me.jurassicraft.client.game.render.Model
import me.jurassicraft.client.game.world.Entity
import me.jurassicraft.client.util.Keys
import me.jurassicraft.client.world.World
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT

lateinit var entity: Entity

fun main() {
    val game = Game(WindowOptions(
        true,
        60,
        20,
        Pair(1280, 720)
    ))

    init(game)
    classScan()
    game.run()
}

fun init(game: Game) {
    // Define the vertices of the triangle
    val positions = floatArrayOf(
        // VO
        -0.5f, 0.5f, 0.5f,  // V1
        -0.5f, -0.5f, 0.5f,  // V2
        0.5f, -0.5f, 0.5f,  // V3
        0.5f, 0.5f, 0.5f,  // V4
        -0.5f, 0.5f, -0.5f,  // V5
        0.5f, 0.5f, -0.5f,  // V6
        -0.5f, -0.5f, -0.5f,  // V7
        0.5f, -0.5f, -0.5f,
    )

    val indices = intArrayOf(
        // Front face
        0, 1, 3, 3, 1, 2,  // Top Face
        4, 0, 3, 5, 4, 3,  // Right face
        3, 2, 7, 5, 3, 7,  // Left face
        6, 1, 0, 6, 0, 4,  // Bottom face
        2, 1, 6, 2, 6, 7,  // Back face
        7, 6, 4, 7, 4, 5,
    )

    val colors = floatArrayOf(
        0.5f, 0.0f, 0.0f,
        0.0f, 0.5f, 0.0f,
        0.0f, 0.0f, 0.5f,
        0.0f, 0.5f, 0.5f,
        0.5f, 0.0f, 0.0f,
        0.0f, 0.5f, 0.0f,
        0.0f, 0.0f, 0.5f,
        0.0f, 0.5f, 0.5f,
    )

    val world = World("JurassiCraft", 100)
    val model = Model()
    entity = Entity(world)
    entity.position = Vector3f(0.0f, 0.0f, -2.0f)

//    var block2 = Entity(world)
//    block2.position = Vector3f(0.0f, 0.0f, -2.0f)

    val mesh = Mesh(positions, indices, colors)
    model.modelParts.add(mesh)
    model.trackedEntities.add(entity)
//    model.trackedEntities.add(block2)

    game.renderer.addModel(model)
}

@EventHandler
fun onMove(event: KeyboardInputEvent) {
    val delta = Vector4f(0f)
    when (event.key) {
        Keys.SPACE -> delta.y = 1f
        Keys.LEFT_SHIFT -> delta.y = -1f
        Keys.W -> delta.z = 1f
        Keys.S -> delta.z = -1f
        Keys.A -> delta.x = -1f
        Keys.D -> delta.x = 1f
        // idk
        Keys.Q -> {
            entity.rotation.rotateAxis(Math.toRadians(5.0).toFloat(), 0f, 1f, 0f)
            entity.update()
            return
        }
    }

    delta.mul(0.1f)
    if (event.isControlDown()) delta.mul(5f)

    entity.position.add(delta.x, delta.y, delta.z)
    entity.scale += delta.w
    entity.update()
}
