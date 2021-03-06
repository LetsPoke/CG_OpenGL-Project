#version 330

float degToRad(in float degree) {

    float pi = 3.141592653589793238462643383279;
    return pi / 180 * degree;
}

vec2 rotate(in vec2 coord, in float deg) {

    deg = degToRad(deg);
    mat2 rotate = mat2(cos(deg), sin(deg), -sin(deg), cos(deg));
    return coord * rotate;
}

layout(location = 0) in vec2 triangle;
layout(location = 1) in vec3 colordata;

out vec3 Farbe;

void main() {

    Farbe = colordata;
    //Farbe = vec3(1.0,0.0,0.0);
    // hier kann Transformation erfolgen
    gl_Position = vec4(rotate(triangle,90), 0.0, 1.0);

}