#version 330

in vec3 Farbe;
out vec3 pixelFarbe;
vec2 currentPixel = gl_FragCoord.xy;

void main(){

    if(currentPixel.x < 250 || currentPixel.x > 450 || currentPixel.y < 250 || currentPixel.y > 450){
        pixelFarbe = vec3(0.0, 1.0, 0.0);
    }else{
        pixelFarbe = Farbe;
    }
}