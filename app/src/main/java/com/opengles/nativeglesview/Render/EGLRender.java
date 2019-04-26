package com.opengles.nativeglesview.Render;


import android.opengl.GLES20;
import android.util.Log;

import com.opengles.nativeglesview.View.EglSurfaceView;
/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/25
 * @description
 */
public class EGLRender implements EglSurfaceView.Render{

    private static String TAG = "EGLrender";
    public EGLRender() {

    }

    @Override
    public void onSurfaceCreated() {
        Log.d(TAG, "onSurfaceCreated: ");
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);

    }

    @Override
    public void onDrawFrame() {
//        Log.d(TAG, "onDrawFrame: \"ondrawFrame\"");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
    }
}


