package me.jurassicraft.client.element

import me.jurassicraft.client.asset.Resource

/**
 * Represents an element that can be textured.
 */
interface Textured {

    /**
     * The resource that this element uses.
     */
    val resource: Resource

}

/**
 * Represents an element that can be hitboxed.
 */
interface Hitboxed {

    /**
     * The height of the element in the X direction.
     */
    val widthX: Int

    /**
     * The height of the element in the Y direction.
     */
    val height: Int

    /**
     * The width of the element in the Z direction.
     */
    val widthZ: Int

    /**
     * Whether players can walk through this element.
     */
    val occluding: Boolean

}