#version 400

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec4 color;

out vec3 normal0;
out vec4 colorV;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 worldMatrix;

void main(){

colorV = color;
gl_Position = projectionMatrix * viewMatrix * worldMatrix * vec4(position, 1.0);

normal0= (worldMatrix * vec4 (normal,0.0)).xyz;

}