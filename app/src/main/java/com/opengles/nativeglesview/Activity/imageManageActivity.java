package com.opengles.nativeglesview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.opengles.nativeglesview.R;
import com.opengles.nativeglesview.View.SGLView;
import com.opengles.nativeglesview.filter.ColorFilter;
import com.opengles.nativeglesview.filter.ContrastColorFilter;
/**
 * Created by action.zhou on 2019/5/5
 */
public class imageManageActivity extends AppCompatActivity {
    private static String TAG = "imageManageActivity";
    private SGLView mGLView;
    private boolean isHalf=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_manage);
//        mGLView= (SGLView) findViewById(R.id.glView);
        mGLView = new SGLView(this);
        setContentView(mGLView);
        mGLView.requestFocus();//获取焦点
        mGLView.setFocusableInTouchMode(true);//设置为可触控
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mDeal:
                isHalf=!isHalf;
                if(isHalf){
                    item.setTitle("处理一半");
                }else{
                    item.setTitle("全部处理");
                }
                mGLView.getRender().refresh();
                break;
            case R.id.mDefault:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.NONE));
                break;
            case R.id.mGray:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.GRAY));
                break;
            case R.id.mCool:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.COOL));
                break;
            case R.id.mWarm:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.WARM));
                break;
            case R.id.mBlur:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.BLUR));
                break;
            case R.id.mMagn:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.MAGN));
                break;
            case R.id.mSep:
                mGLView.setFilter(new ContrastColorFilter(this, ColorFilter.Filter.SEPAR));
                break;
        }
        mGLView.getRender().getFilter().setHalf(isHalf);
        mGLView.requestRender();
        return super.onOptionsItemSelected(item);
    }
}
