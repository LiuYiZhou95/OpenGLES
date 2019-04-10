package com.opengles.nativeglesview;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.opengles.nativeglesview.View.MySurfaceView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new MySurfaceView(this);
        setContentView(mGLView);
    }
}
