package me.jurassicraft.client

import me.jurassicraft.client.event.EventHandler
import me.jurassicraft.client.event.KeyboardInputEvent
import me.jurassicraft.client.event.classScan
import me.jurassicraft.client.game.WindowOptions
import me.jurassicraft.client.game.render.Mesh
import me.jurassicraft.client.game.render.Model
import me.jurassicraft.client.game.render.TextureOption
import me.jurassicraft.client.game.world.Entity
import me.jurassicraft.client.util.Keys
import me.jurassicraft.client.world.World
import org.joml.Vector3f
import org.joml.Vector4f

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
    val positions = floatArrayOf(
        // V0
        -0.5f, 0.5f, 0.5f,  // V1
        -0.5f, -0.5f, 0.5f,  // V2
        0.5f, -0.5f, 0.5f,  // V3
        0.5f, 0.5f, 0.5f,  // V4
        -0.5f, 0.5f, -0.5f,  // V5
        0.5f, 0.5f, -0.5f,  // V6
        -0.5f, -0.5f, -0.5f,  // V7
        0.5f, -0.5f, -0.5f,  // For text coords in top face
        // V8: V4 repeated

        -0.5f, 0.5f, -0.5f,  // V9: V5 repeated
        0.5f, 0.5f, -0.5f,  // V10: V0 repeated
        -0.5f, 0.5f, 0.5f,  // V11: V3 repeated
        0.5f, 0.5f, 0.5f,  // For text coords in right face
        // V12: V3 repeated

        0.5f, 0.5f, 0.5f,  // V13: V2 repeated
        0.5f, -0.5f, 0.5f,  // For text coords in left face
        // V14: V0 repeated

        -0.5f, 0.5f, 0.5f,  // V15: V1 repeated
        -0.5f, -0.5f, 0.5f,  // For text coords in bottom face
        // V16: V6 repeated

        -0.5f, -0.5f, -0.5f,  // V17: V7 repeated
        0.5f, -0.5f, -0.5f,  // V18: V1 repeated
        -0.5f, -0.5f, 0.5f,  // V19: V2 repeated
        0.5f, -0.5f, 0.5f,
    )

    val textCoords = floatArrayOf(
        0.0f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,
        0.5f, 0.0f,

        0.0f, 0.0f,
        0.5f, 0.0f,
        0.0f, 0.5f,
        0.5f, 0.5f,  // For text coords in top face

        0.0f, 0.5f,
        0.5f, 0.5f,
        0.0f, 1.0f,
        0.5f, 1.0f,  // For text coords in right face

        0.0f, 0.0f,
        0.0f, 0.5f,  // For text coords in left face

        0.5f, 0.0f,
        0.5f, 0.5f,  // For text coords in bottom face

        0.5f, 0.0f,
        1.0f, 0.0f,
        0.5f, 0.5f,
        1.0f, 0.5f,
    )
    val indices = intArrayOf(
        // Front face
        0, 1, 3, 3, 1, 2,  // Top Face
        8, 10, 11, 9, 8, 11,  // Right face
        12, 13, 7, 5, 12, 7,  // Left face
        14, 15, 6, 4, 14, 6,  // Bottom face
        16, 18, 19, 17, 16, 19,  // Back face
        4, 6, 7, 5, 4, 7,
    )

    val world = World("JurassiCraft", 100)
    val model = Model()
    entity = Entity(world)
    entity.position = Vector3f(0.0f, 0.0f, -2.0f)
    println(entity.rotation)

//    var block2 = Entity(world)
//    block2.position = Vector3f(0.0f, 0.0f, -2.0f)

    val texture = game.textureManager["/assets/textures/blocks/default.png"]
    val mesh = Mesh(positions, indices, TextureOption(texture, textCoords))
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
