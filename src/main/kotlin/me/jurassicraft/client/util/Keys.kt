package me.jurassicraft.client.util

import me.jurassicraft.client.client.render.Window
import org.lwjgl.glfw.GLFW.*

fun isKeyDown(window: Window, key: Int): Boolean {
    return glfwGetKey(window.id, key) == GLFW_PRESS
}

object Keys {
    const val NUM_0: Int = GLFW_KEY_0
    const val NUM_1: Int = GLFW_KEY_1
    const val NUM_2: Int = GLFW_KEY_2
    const val NUM_3: Int = GLFW_KEY_3
    const val NUM_4: Int = GLFW_KEY_4
    const val NUM_5: Int = GLFW_KEY_5
    const val NUM_6: Int = GLFW_KEY_6
    const val NUM_7: Int = GLFW_KEY_7
    const val NUM_8: Int = GLFW_KEY_8
    const val NUM_9: Int = GLFW_KEY_9
    const val A: Int = GLFW_KEY_A
    const val B: Int = GLFW_KEY_B
    const val C: Int = GLFW_KEY_C
    const val D: Int = GLFW_KEY_D
    const val E: Int = GLFW_KEY_E
    const val F: Int = GLFW_KEY_F
    const val G: Int = GLFW_KEY_G
    const val H: Int = GLFW_KEY_H
    const val I: Int = GLFW_KEY_I
    const val J: Int = GLFW_KEY_J
    const val K: Int = GLFW_KEY_K
    const val L: Int = GLFW_KEY_L
    const val M: Int = GLFW_KEY_M
    const val N: Int = GLFW_KEY_N
    const val O: Int = GLFW_KEY_O
    const val P: Int = GLFW_KEY_P
    const val Q: Int = GLFW_KEY_Q
    const val R: Int = GLFW_KEY_R
    const val S: Int = GLFW_KEY_S
    const val T: Int = GLFW_KEY_T
    const val U: Int = GLFW_KEY_U
    const val V: Int = GLFW_KEY_V
    const val W: Int = GLFW_KEY_W
    const val X: Int = GLFW_KEY_X
    const val Y: Int = GLFW_KEY_Y
    const val Z: Int = GLFW_KEY_Z
    const val LEFT_SHIFT: Int = GLFW_KEY_LEFT_SHIFT
    const val RIGHT_SHIFT: Int = GLFW_KEY_RIGHT_SHIFT
    const val LEFT_CONTROL: Int = GLFW_KEY_LEFT_CONTROL
    const val RIGHT_CONTROL: Int = GLFW_KEY_RIGHT_CONTROL
    const val LEFT_ALT: Int = GLFW_KEY_LEFT_ALT
    const val RIGHT_ALT: Int = GLFW_KEY_RIGHT_ALT
    const val SPACE: Int = GLFW_KEY_SPACE
    const val TAB: Int = GLFW_KEY_TAB
    const val ENTER: Int = GLFW_KEY_ENTER
    const val BACKSPACE: Int = GLFW_KEY_BACKSPACE
    const val ESCAPE: Int = GLFW_KEY_ESCAPE
    const val DELETE: Int = GLFW_KEY_DELETE
    const val UP: Int = GLFW_KEY_UP
}
