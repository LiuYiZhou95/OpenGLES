package com.opengles.nativeglesview;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.opengles.nativeglesview.View.MySurfaceView;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.fboActivityButton);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.FBO_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.commonTranglesActivityButton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.COMMONTRANGLES_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });
        Button button3=(Button)findViewById(R.id.nativeTranglesActivityButton);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.NATIVETRANGLES_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });

        Button button4=(Button)findViewById(R.id.eglActivityButton);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.EGL_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });

        Button button5=(Button)findViewById(R.id.eglsurfaceActivityButton);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.EGLSURFACE_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });

        Button button6=(Button)findViewById(R.id.imagemanageActivityButton);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent("com.opengles.nativeglesview.IMAGEMANAGE_ACTIVITY_ACTION");
                startActivity(intent);
            }
        });


    }
}
