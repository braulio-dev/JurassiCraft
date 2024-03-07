package me.jurassicraft.client

import me.jurassicraft.client.event.classScan
import me.jurassicraft.client.client.Client
import me.jurassicraft.client.client.render.Options
import me.jurassicraft.client.client.render.model.Mesh
import me.jurassicraft.client.client.render.model.Model
import me.jurassicraft.client.client.render.model.TextureOption
import me.jurassicraft.client.client.render.model.ModeledObject
import me.jurassicraft.client.common.world.World
import org.joml.Vector3f

lateinit var entity: ModeledObject

fun main() {
    val game = Client(
        Options(
            true,
            0,
            20,
            Pair(1280, 720)
        )
    )

    init(game)
    classScan()
    game.run()
}

fun init(game: Client) {
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
    entity = ModeledObject(world)
    entity.position = Vector3f(0.0f, 0.0f, -2.0f)

    val texture = game.assetManager.getTexture("/blocks/grass_block.png")
    val mesh = Mesh(positions, indices, TextureOption(texture, textCoords))
    model.modelParts.add(mesh)
    model.trackedEntities.add(entity)

    game.renderer.addModel(model)
}
