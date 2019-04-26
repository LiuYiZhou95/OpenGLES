package com.opengles.nativeglesview.Activity;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.opengles.nativeglesview.Render.FBORenderer;
import com.opengles.nativeglesview.View.MySurfaceView;
/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/14
 * @description
 */
public class FBOActivity extends AppCompatActivity {
    private static String TAG = "FBOActivity";

    private FBORenderer fboRenderer;
    private GLSurfaceView mGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fboRenderer = new FBORenderer(this);
        mGLView = new MySurfaceView(this,fboRenderer);
        setContentView(mGLView);
    }
}
