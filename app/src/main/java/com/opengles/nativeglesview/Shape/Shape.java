package com.opengles.nativeglesview.Shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.opengles.nativeglesview.R;
import com.opengles.nativeglesview.Render.FBORenderer;
import com.opengles.nativeglesview.View.MySurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Shape {
	private static String TAG = "Shape";
	private int TextureId;
	private Context mContext;

	private float mVertexArray[]=new float[9];
	private float mColorArray[]=new float[12];
	private int mVertexCount;

	private FloatBuffer mVertexBuffer;//顶点坐标数据缓冲
	private FloatBuffer mCoorBuffer;//顶点纹理坐标数据缓冲
	private int mProgram;

	public Shape(Context context) {
		this.mContext = context;
		initVetexData();
	}

	public void initVetexData() {

		mVertexArray[0]=0.0f;mVertexArray[1]=0.0f;mVertexArray[2]=0.0f;
		mVertexArray[3]=0.5f;mVertexArray[4]=-0.5f;mVertexArray[5]=0.0f;
		mVertexArray[6]=-0.5f;mVertexArray[7]=-0.5f;mVertexArray[8]=0.0f;

		mColorArray[0]=0.0f;mColorArray[1]=0.0f;mColorArray[2]=1.0f;mColorArray[3]=0.0f;
		mColorArray[4]=0.0f;mColorArray[5]=0.0f;mColorArray[6]=1.0f;mColorArray[7]=0.0f;
		mColorArray[8]=0.0f;mColorArray[9]=0.0f;mColorArray[10]=1.0f;mColorArray[11]=0.0f;

		mVertexCount=mVertexArray.length/3;

		//创建顶点数据缓冲
		mVertexBuffer=ByteBuffer.allocateDirect(mVertexArray.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mVertexBuffer.put(mVertexArray);
		//设置缓冲区初始位置
		mVertexBuffer.position(0);

		mCoorBuffer=ByteBuffer.allocateDirect(mColorArray.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCoorBuffer.put(mColorArray);
		mCoorBuffer.position(0);
	}

	public void draw(float[] mvpMatrix, float[] mMatrix){
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		int frameBufferVertexShader = loaderShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int frameBufferFagmentShader = loaderShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

		mProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(mProgram, frameBufferVertexShader);
		GLES20.glAttachShader(mProgram, frameBufferFagmentShader);
		GLES20.glLinkProgram(mProgram);

		int fbPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		int fbColorCoordHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
		int fbuMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
		int fbuTextureHandle = GLES20.glGetUniformLocation(mProgram, "uTexture");
		GLES20.glUseProgram(mProgram);
		mVertexBuffer.position(0);
		GLES20.glVertexAttribPointer(fbPositionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, mVertexBuffer);
		mCoorBuffer.position(0);
		GLES20.glVertexAttribPointer(fbColorCoordHandle, 4, GLES20.GL_FLOAT, false, 4 * 4, mCoorBuffer);
		mCoorBuffer.position(0);

		GLES20.glEnableVertexAttribArray(fbPositionHandle);
		GLES20.glEnableVertexAttribArray(fbColorCoordHandle);

		GLES20.glUniformMatrix4fv(fbuMVPMatrixHandle, 1, false, mvpMatrix, 0);



		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVertexCount);
	}

	public void initTexture(int res) {
		int [] textures = new int[1];
		GLES20.glGenTextures(1, textures, 0);
		TextureId = textures[0];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, TextureId);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_MIRRORED_REPEAT);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_MIRRORED_REPEAT);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), res);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}


	private int loaderShader(int type, String shaderCode) {
		int shader = GLES20.glCreateShader(type);
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		return shader;
	}
	


	private String fragmentShaderCode = "precision mediump float;\n" +
			"varying  vec4 vColor;\n" +
			"void main()\n" +
			"{\n" +
			"    gl_FragColor = vColor;\n" +
			"}\n";

	private String vertexShaderCode = "attribute vec4 aPosition;\n" +
			"attribute vec4 aColor;\n" +
			"varying vec4 vColor;\n" +
			"uniform mat4 uMVPMatrix;\n" +
			"void main()\n" +
			"{\n" +
			"    gl_Position = uMVPMatrix * aPosition;\n" +
			"    vColor = aColor;\n" +
			"}\n";


}

