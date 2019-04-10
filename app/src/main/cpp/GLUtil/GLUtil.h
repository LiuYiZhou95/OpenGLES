//
// Created by B612 on 2019/4/8.
//

#ifndef OPENGLES_GLUTIL_H
#define OPENGLES_GLUTIL_H

#include <GLES2/gl2.h>
#include <android/log.h>

#define LOGI(level, ...) __android_log_print(ANDROID_LOG_INFO, "NATIVE_LOG", __VA_ARGS__)
class GLUtil {
    public:
        static int complieShader(int type, const char* shaderCode);
        static int createProgram(const char * vertexShaderCode, const char * fragmentShaderCode);

};


#endif //OPENGLES_GLUTIL_H
