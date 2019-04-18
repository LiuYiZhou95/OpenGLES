package com.opengles.nativeglesview.View;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.opengles.nativeglesview.Render.FBORenderer;
import com.opengles.nativeglesview.Render.MyRenderer;
/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/8
 * @description
 */
public class MySurfaceView extends GLSurfaceView {

    public MySurfaceView (Context context,GLSurfaceView.Renderer renderer){
        super(context);
        this.setEGLContextClientVersion(2);
        this.setRenderer(renderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


}
