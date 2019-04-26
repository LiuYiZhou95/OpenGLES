package com.opengles.nativeglesview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opengles.nativeglesview.Render.EGLRender;
import com.opengles.nativeglesview.View.EglSurfaceView;
/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/25
 * @description
 */
public class EglSurfaceActivity extends AppCompatActivity {
    private static String TAG = "EglSurfaceActivity";

    private EGLRender eglRenderer;
    private EglSurfaceView eglSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eglRenderer = new EGLRender();
        eglSurfaceView = new EglSurfaceView(this);
        eglSurfaceView.setRender(eglRenderer);
        setContentView(eglSurfaceView);
    }


}
