package com.opengles.nativeglesview.Activity;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opengles.nativeglesview.Render.CommonTranglesRenderer;
import com.opengles.nativeglesview.Render.MyRenderer;
import com.opengles.nativeglesview.View.MySurfaceView;

public class CommonTranglesActivity extends AppCompatActivity {
    private CommonTranglesRenderer myRenderer;
    private GLSurfaceView mGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRenderer = new CommonTranglesRenderer(this);
        mGLView = new MySurfaceView(this,myRenderer);
        setContentView(mGLView);
    }
}
