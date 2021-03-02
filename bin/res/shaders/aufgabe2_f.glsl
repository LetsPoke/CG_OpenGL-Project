#version 330

in vec3 Farbe;
out vec3 pixelFarbe;
vec2 currentPixel = gl_FragCoord.xy;

void main(){
    pixelFarbe = Farbe;

    if(currentPixel.x < 150 || currentPixel.x > 550 || currentPixel.y < 150 || currentPixel.y > 550){
        pixelFarbe = vec3(0.0, 1.0, 0.0);
    }
}