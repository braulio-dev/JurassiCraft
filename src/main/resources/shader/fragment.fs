#version 330

in vec2 fragTexture;
out vec4 texture;

uniform sampler2D tex_sampler;

void main() {
    texture = texture(tex_sampler, fragTexture);
}