package me.jurassicraft.client.element.impl

import me.jurassicraft.client.asset.Resource
import me.jurassicraft.client.element.Element3D

class Cube(val resource: Resource, widthX: Int, height: Int, widthZ: Int) : Element3D(widthX, height, widthZ) {

    override fun render() {
        // Render GL cube

    }

}