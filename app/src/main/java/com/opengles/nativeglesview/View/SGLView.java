/*
 *
 * SGLView.java
 * 
 * Created by Wuwang on 2016/10/15
 * Copyright © 2016年 深圳哎吖科技. All rights reserved.
 */
package com.opengles.nativeglesview.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.opengles.nativeglesview.Render.SGLRender;
import com.opengles.nativeglesview.filter.AFilter;

import java.io.IOException;


/**
 * Description:
 */
public class SGLView extends GLSurfaceView {

    private SGLRender render;

    public SGLView(Context context) {
        this(context,null);
    }

    public SGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setEGLContextClientVersion(2);
        render=new SGLRender(this);
        setRenderer(render);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        try {
            render.setImage(BitmapFactory.decodeStream(getResources().getAssets().open("texture/fengmian.jpg")));
            requestRender();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SGLRender getRender(){
        return render;
    }

    public void setFilter(AFilter filter){
        render.setFilter(filter);
    }

}
