#version 330

in vec2 fragTexture;
out vec4 tex;

uniform sampler2D tex_sampler;

void main() {
    tex = texture(tex_sampler, fragTexture);
}