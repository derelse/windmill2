#version 400

in vec3 normal0;
in vec4 colorV;

uniform vec3 sun;
uniform sampler2D texture_sampler;

const float ambientLight = 0.1;

void main(){

float brightness = max(ambientLight, dot(normal0, sun));
gl_FragColor = brightness * colorV;

}
