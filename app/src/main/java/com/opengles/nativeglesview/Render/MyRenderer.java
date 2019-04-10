package com.opengles.nativeglesview.Render;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.opengles.nativeglesview.util.*;
/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/8
 * @description
 */
public class MyRenderer implements GLSurfaceView.Renderer{
    public float mAngleX=0;
    public float mAngleY=0;
    private Context mContext;
    public MyRenderer(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        String vShaderStr= ShaderUtil.getVShaderSource(mContext,ShaderSoucreType.NONE);
        String fShaderStr= ShaderUtil.getFShaderSource(mContext,ShaderSoucreType.NONE);
        nativeInit(vShaderStr, fShaderStr);


    }

    @Override
    public  void onDrawFrame(GL10 gl10){
        nativeDraw(mAngleX, mAngleY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        nativeSurfaceChanged(width, height);
    }

    static {
        System.loadLibrary("native-lib");
    }

    public static native void nativeInit(String vertexShaderCode, String fragmentShaderCode);
    private static native void nativeDraw(float angleX, float angleY);
    private static native void nativeSurfaceChanged(int width, int height);
}
