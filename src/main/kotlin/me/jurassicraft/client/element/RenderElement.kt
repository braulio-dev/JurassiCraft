package me.jurassicraft.client.element

import me.jurassicraft.client.asset.Resource

/**
 * Represents an element that can be rendered.
 */
interface RenderElement {

    /**
     * Renders the element.
     */
    fun render()

}

abstract class Element2D(val width: Int, val height: Int) : RenderElement

abstract class Element3D(val widthX: Int, val height: Int, val widthZ: Int) : RenderElement