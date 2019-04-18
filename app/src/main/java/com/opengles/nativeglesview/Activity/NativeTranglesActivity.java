package com.opengles.nativeglesview.Activity;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opengles.nativeglesview.Render.MyRenderer;
import com.opengles.nativeglesview.View.MySurfaceView;

public class NativeTranglesActivity extends AppCompatActivity {

    private MyRenderer fboRenderer;
    private GLSurfaceView mGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fboRenderer = new MyRenderer(this);
        mGLView = new MySurfaceView(this,fboRenderer);
        setContentView(mGLView);
    }
}
