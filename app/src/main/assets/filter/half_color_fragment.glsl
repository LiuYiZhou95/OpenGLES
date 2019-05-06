precision mediump float;

uniform sampler2D vTexture;
uniform int vChangeType;
uniform vec3 vChangeColor;
uniform int vIsHalf;
uniform float uXY;
uniform float time;

varying vec4 gPosition;

varying vec2 aCoordinate;
varying vec4 aPos;

void modifyColor(vec4 color){
    color.r=max(min(color.r,1.0),0.0);
    color.g=max(min(color.g,1.0),0.0);
    color.b=max(min(color.b,1.0),0.0);
    color.a=max(min(color.a,1.0),0.0);
}
//冷色调暖色调
void coolwarmColor(vec4 nColor ){
    vec4 deltaColor=nColor+vec4(vChangeColor,0.0);
    modifyColor(deltaColor);
    gl_FragColor=deltaColor;
}
//模糊
void bulrColor(vec4 nColor){
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.r,aCoordinate.y-vChangeColor.r));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.r,aCoordinate.y+vChangeColor.r));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.r,aCoordinate.y-vChangeColor.r));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.r,aCoordinate.y+vChangeColor.r));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.g,aCoordinate.y-vChangeColor.g));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.g,aCoordinate.y+vChangeColor.g));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.g,aCoordinate.y-vChangeColor.g));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.g,aCoordinate.y+vChangeColor.g));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.b,aCoordinate.y-vChangeColor.b));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x-vChangeColor.b,aCoordinate.y+vChangeColor.b));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.b,aCoordinate.y-vChangeColor.b));
    nColor+=texture2D(vTexture,vec2(aCoordinate.x+vChangeColor.b,aCoordinate.y+vChangeColor.b));
    nColor/=13.0;
    gl_FragColor=nColor;
}
//放大镜
void bigCameraColor(vec4 nColor){
    float dis=distance(vec2(gPosition.x,gPosition.y/uXY),vec2(vChangeColor.r,vChangeColor.g));
    if(dis<vChangeColor.b){
        nColor=texture2D(vTexture,vec2(aCoordinate.x/2.0+0.25,aCoordinate.y/2.0+0.25));
    }
    gl_FragColor=nColor;
}
//分通道
void separateColor(vec4 nColor){
    vec4 bn = vec4(vec3(nColor.r+nColor.g+nColor.b)/3.,1.0);
    vec2 offset = vec2(abs(sin(time))/30.,0.02);
    nColor.r = texture2D(vTexture,aCoordinate).r;
    nColor.g = texture2D(vTexture,aCoordinate-offset).g;
    nColor.b = texture2D(vTexture,aCoordinate+offset).b;
    gl_FragColor=mix(nColor,bn,0.3);


}

void main(){
    vec4 nColor=texture2D(vTexture,aCoordinate);


    if(aPos.x>0.0||vIsHalf==0){
        if(vChangeType==1){


        }else if(vChangeType==2){
            coolwarmColor(nColor );
        }else if(vChangeType==3){
            bulrColor(nColor);
        }else if(vChangeType==4){
            bigCameraColor(nColor);
        }else if(vChangeType==5){
            separateColor( nColor);
        }
        else{
            gl_FragColor=nColor;
        }
    }else{
        gl_FragColor=nColor;
    }
}