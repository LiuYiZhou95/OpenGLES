//
// Created by B612 on 2019/4/8.
//

#ifndef OPENGLES_TRANGLES_H
#define OPENGLES_TRANGLES_H

#include <GLES2/gl2.h>

#define PI 3.141592
class Trangles {
public:
    GLint mProgram;
    GLint mUMVPMatrixHandle;
    GLint mAPositionHandle;
    GLint mAColorHandle;
    GLfloat *mVertexArray;
    GLfloat *mColorArray;

    Trangles();
    virtual ~Trangles();
    void initVertex();
    void initGL(const char *vertexShaderCode, const char *fragmentShaderCode);
    void draw(float mvpMatrix[]);
};


#endif //OPENGLES_TRANGLES_H
