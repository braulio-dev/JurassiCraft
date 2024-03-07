#version 330

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 texture;
out vec2 fragTexture;

uniform mat4 projectionMatrix;
uniform mat4 modelViewMatrix;
uniform mat4 worldViewMatrix;

void main()
{
    gl_Position = projectionMatrix * worldViewMatrix * modelViewMatrix * vec4(position, 1.0);
    fragTexture = texture;
}
