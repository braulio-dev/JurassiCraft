package me.jurassicraft.client.game.render

import me.jurassicraft.client.game.world.Entity

/**
 * Represents an element that can be rendered.
 */
open class Model {

    internal val modelParts = mutableListOf<Mesh>()
    internal val trackedEntities = mutableListOf<Entity>()
    
    /**
     * Determines whether the element should be removed from the render queue.
     */
    open fun shouldRender(): Boolean = true

    /**
     * Destroys the element.
     */
    fun destroy() = modelParts.forEach { it.destroy() }

}