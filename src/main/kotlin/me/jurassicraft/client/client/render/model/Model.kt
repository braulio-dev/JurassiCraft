package me.jurassicraft.client.client.render.model

/**
 * Represents an element that can be rendered.
 */
open class Model {

    internal val modelParts = mutableListOf<Mesh>()
    internal val trackedEntities = mutableListOf<ModeledObject>()

    /**
     * Destroys the element.
     */
    fun destroy() = modelParts.forEach { it.destroy() }

}