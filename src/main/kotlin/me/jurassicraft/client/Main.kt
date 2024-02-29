package me.jurassicraft.client

import me.jurassicraft.client.game.Game
import me.jurassicraft.client.game.WindowOptions

fun main() {
    val game = Game(WindowOptions(
        true,
        60,
        20,
        Pair(1280, 720)
    ))
    game.run()
}
