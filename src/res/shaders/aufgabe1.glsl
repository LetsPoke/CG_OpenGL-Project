#version 330

out vec3 pixelFarbe;
vec2 currentPixel = gl_FragCoord.xy;


mat2 rotate2d(in float a){
    mat2 m = mat2(cos(a), sin(a), -sin(a), cos(a));
    return m;
}

void draw(){
    pixelFarbe = vec3(1.0, 0.0, 0.0);
    //Aufgabe 1
    currentPixel = currentPixel * rotate2d(0.2);

    if(currentPixel.x < 15 || currentPixel.x > 685 || currentPixel.y < 15 || currentPixel.y > 685){
        pixelFarbe = vec3(0.0, 1.0, 0.0);
    }

}

void main(){
   draw();

    //Aufgabe 2/3
    vec2 circleCenter = vec2(150, 550);
    float d = distance(currentPixel,circleCenter);
    if(d < 100){ pixelFarbe = vec3(0.0, 1.0, 0.0);}
    vec2 circleCenter2 = vec2(550, 550);
    float d2 = distance(currentPixel,circleCenter2);
    if(d2 < 100){ pixelFarbe = vec3(0.0, 1.0, 0.0);}

    vec2 circleCenter3 = vec2(350, 150);
    float d3 = distance(currentPixel,circleCenter3);
    if(d3 < 50){ pixelFarbe = vec3(0.0, 1.0, 0.0);}
    vec2 circleCenter4 = vec2(250, 150);
    float d4 = distance(currentPixel,circleCenter4);
    if(d4 < 50){ pixelFarbe = vec3(0.0, 1.0, 0.0);}
    vec2 circleCenter5 = vec2(150, 150);
    float d5 = distance(currentPixel,circleCenter5);
    if(d5 < 50){ pixelFarbe = vec3(0.0, 1.0, 0.0);}
    vec2 circleCenter6 = vec2(450, 150);
    float d6 = distance(currentPixel,circleCenter6);
    if(d6 < 50){ pixelFarbe = vec3(0.0, 1.0, 0.0);}
    vec2 circleCenter7 = vec2(550, 150);
    float d7 = distance(currentPixel,circleCenter7);
    if(d7 < 50){ pixelFarbe = vec3(0.0, 1.0, 0.0);}

    vec2 circleCenter8 = vec2(130, 530);
    float d8 = distance(currentPixel,circleCenter8);
    if(d8 < 50){ pixelFarbe = vec3(0.0, 0.0, 1.0);}
    vec2 circleCenter9 = vec2(530, 530);
    float d9 = distance(currentPixel,circleCenter9);
    if(d9 < 50){ pixelFarbe = vec3(0.0, 0.0, 1.0);}

}
