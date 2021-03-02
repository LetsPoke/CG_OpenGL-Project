#version 330

layout (location = 0) in vec3 aPos;
layout(location = 1) in vec3 colordata;
layout (location = 2 ) in vec2 textCoords;

uniform mat4 projectionsM;
uniform mat4 transMat;

out vec2 TexCoord;
out vec3 Farbe;

void main()
{
    Farbe = colordata;

    gl_Position = projectionsM * transMat * vec4(aPos, 1.0f);
    TexCoord = textCoords;
}
