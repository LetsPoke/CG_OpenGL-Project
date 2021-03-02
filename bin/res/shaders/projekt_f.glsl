#version 330

in vec3 Farbe;
in vec2 TexCoord;

out vec3 pixelFarbe;
out vec4 out_Color;

uniform sampler2D texturSampler;

vec2 currentPixel = gl_FragCoord.xy;

void main(){

    vec4 out_Color = texture(texturSampler, TexCoord);

    if(currentPixel.x < 250 || currentPixel.x > 450 || currentPixel.y < 250 || currentPixel.y > 450){
        pixelFarbe = vec3(0.0, 1.0, 0.0);
    }else{
        pixelFarbe = Farbe;
    }
}