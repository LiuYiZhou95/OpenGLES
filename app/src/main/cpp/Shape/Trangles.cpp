//
// Created by B612 on 2019/4/8.
//

#include <src/main/cpp/GLUtil/GLUtil.h>
#include "Trangles.h"

void Trangles::initGL(const char *vertexShaderCode, const char *fragmentShaderCode) {
    mProgram = GLUtil::createProgram(vertexShaderCode, fragmentShaderCode);
    mUMVPMatrixHandle = glGetUniformLocation(mProgram, "uMVPMatrix");
    mAPositionHandle = glGetAttribLocation(mProgram, "aPosition");
    mAColorHandle = glGetAttribLocation(mProgram, "aColor");
}
void Trangles::initVertex() {
    mVertexArray = new GLfloat[3*3];
    mColorArray = new GLfloat[3*4];

    mVertexArray[0]=0;mVertexArray[1]=0;mVertexArray[2]=0;
    mVertexArray[3]=1;mVertexArray[4]=-1;mVertexArray[5]=0;
    mVertexArray[6]=-1;mVertexArray[7]=-1;mVertexArray[8]=0;

    mColorArray[0]=1;mColorArray[1]=0;mColorArray[2]=0;mColorArray[3]=0;
    mColorArray[4]=0;mColorArray[5]=1;mColorArray[6]=0;mColorArray[7]=0;
    mColorArray[8]=0;mColorArray[9]=0;mColorArray[10]=1;mColorArray[11]=0;

}

void Trangles::draw(float mvpMatrix[]) {
    glUseProgram(mProgram);
    // 将顶点数据传递到管线，顶点着色器
    glUniformMatrix4fv(mUMVPMatrixHandle, 1, GL_FALSE, mvpMatrix);
    glVertexAttribPointer(mAPositionHandle,3,GL_FLOAT,GL_FALSE,3*4,mVertexArray);

    glVertexAttribPointer(mAColorHandle,4,GL_FLOAT,GL_FALSE,4*4,mColorArray);

    glEnableVertexAttribArray(mAColorHandle);
    glEnableVertexAttribArray(mAPositionHandle);
    glDrawArrays(GL_TRIANGLE_FAN,0,3);
}
Trangles::Trangles() {
    initVertex();
}

Trangles::~Trangles() {
    delete []mVertexArray;
    delete []mColorArray;
}