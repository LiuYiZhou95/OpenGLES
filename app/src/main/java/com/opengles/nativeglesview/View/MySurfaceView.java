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
    private MyRenderer mRenderer;
    private FBORenderer fboRenderer;

    public MySurfaceView (Context context){
        super(context);
        this.setEGLContextClientVersion(2);
//        mRenderer=new MyRenderer(context);
//        this.setRenderer(mRenderer);
        fboRenderer = new FBORenderer(context);
        this.setRenderer(fboRenderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


}
