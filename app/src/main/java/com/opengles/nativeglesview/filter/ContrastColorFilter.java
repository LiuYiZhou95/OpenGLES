package com.opengles.nativeglesview.filter;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by action.zhou on 2019/5/5
 */
public class ContrastColorFilter extends AFilter {

    private ColorFilter.Filter filter;

    private int hChangeType;
    private int hChangeColor;

    public ContrastColorFilter(Context context, ColorFilter.Filter filter) {
        super(context, "filter/half_color_vertex.glsl", "filter/half_color_fragment.glsl");
        this.filter=filter;
    }

    @Override
    public void onDrawSet() {
        GLES20.glUniform1i(hChangeType,filter.getType());
        GLES20.glUniform3fv(hChangeColor,1,filter.data(),0);
    }

    @Override
    public void onDrawCreatedSet(int mProgram) {

        hChangeType= GLES20.glGetUniformLocation(mProgram,"vChangeType");
        hChangeColor= GLES20.glGetUniformLocation(mProgram,"vChangeColor");
    }

}
