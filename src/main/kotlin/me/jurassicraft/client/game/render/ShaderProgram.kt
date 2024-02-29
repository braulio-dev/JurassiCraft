package me.jurassicraft.client.game.render

import org.joml.Matrix4f
import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.FloatBuffer

class ShaderProgram {

    private var program: Int = -999
    private var vertexShader: Int = -999
    private var fragmentShader: Int = -999
    private val uniforms = mutableMapOf<String, Int>()

    init {
        program = glCreateProgram()
        if (program == 0) {
            error("Failed to create shader program")
        }
    }

    fun setUniform(uniformName: String, mat4: Matrix4f) {
        MemoryStack.stackPush().use {
            val buffer = it.mallocFloat(16)
            mat4.get(buffer)
            glUniformMatrix4fv(uniforms[uniformName]!!, false, buffer)
        }
    }

    fun createUniform(uniformName: String) {
        val uniformLocation = glGetUniformLocation(program, uniformName)
        if (uniformLocation < 0) {
            error("Could not find uniform: $uniformName")
        }
        uniforms[uniformName] = uniformLocation
    }

    fun createVertexShader(shaderFile: String) {
        vertexShader = createShader(readShaderFile(shaderFile), GL_VERTEX_SHADER)
    }

    fun createFragmentShader(shaderFile: String) {
        fragmentShader = createShader(readShaderFile(shaderFile), GL_FRAGMENT_SHADER)
    }

    private fun createShader(shaderCode: String, type: Int): Int {
        val shader = glCreateShader(type)
        if (shader == 0) {
            throw IllegalStateException("Failed to create shader")
        }

        glShaderSource(shader, shaderCode)
        glCompileShader(shader)

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            error("Failed to compile shader: ${glGetShaderInfoLog(shader)}")
        }

        glAttachShader(program, shader)
        return shader
    }

    fun link() {
        glLinkProgram(program)
        if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
            throw IllegalStateException("Failed to link program: ${glGetProgramInfoLog(program)}")
        }

        if (vertexShader != -999) {
            glDetachShader(program, vertexShader)
        }
        if (fragmentShader != -999) {
            glDetachShader(program, fragmentShader)
        }

        glValidateProgram(program)
        if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
            error("Warning validating program: ${glGetProgramInfoLog(program)}")
        }
    }

    fun bind() {
        glUseProgram(program)
    }

    fun unbind() {
        glUseProgram(0)
    }

    fun destroy() {
        unbind()
        if (program != 0) {
            glDeleteProgram(program)
        }
    }

    private fun readShaderFile(fileName: String): String {
        val shaderSource = StringBuilder()

        val inputStream = Renderer::class.java.getResourceAsStream(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream!!))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            shaderSource.append(line).append("\n")
        }
        reader.close()

        return shaderSource.toString()
    }

}