package me.jurassicraft.client.asset

import me.jurassicraft.client.element.Namespace

/**
 * Represents a resource that can be loaded from the classpath.
 */
data class Resource(val namespace: Namespace, val path: String) {
    val fullPath = "${namespace.name}/$path"
}