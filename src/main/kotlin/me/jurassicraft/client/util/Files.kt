package me.jurassicraft.client.util

import java.io.File

fun getFiles(folderPath: String, nested: Boolean = false): List<File> {
    val folder = File(folderPath)
    if (!folder.exists()) {
        throw IllegalArgumentException("Folder does not exist: $folderPath")
    }
    if (!folder.isDirectory) {
        throw IllegalArgumentException("Path is not a folder: $folderPath")
    }
    return if (nested) {
        folder.walk().filter { it.isFile }.toList()
    } else {
        folder.listFiles()?.filter { it.isFile }?.toList() ?: emptyList()
    }
}