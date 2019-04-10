package com.opengles.nativeglesview.Shape;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.opengles.nativeglesview.R;
import com.opengles.nativeglesview.Render.FBORenderer;

public class Shape_FBO {
	private static String TAG = "Shape";
	private float vertices[];
	private float texures[];
	private float normals[];

	private int mVertexCount;

	private FloatBuffer mSqureBuffer;
	private FloatBuffer mSqureBufferfbo;

	private int mFrameBufferProgram;
	private int mWindowProgram;
	private int mLoadedTextureId;
	private Context mContext;
	private float mVertexArray[]=new float[9];
	private float mColorArray[]=new float[8];

	private FloatBuffer mCoorBuffer;//顶点纹理坐标数据缓冲
	private FloatBuffer mVertexBuffer;

	public Shape_FBO(Context context) {
		this.mContext = context;
		initVetexData();

	}

	public void initVetexData() {
		initTexture(R.drawable.texture1);
		float [] squareVertexs = new float[] {
				-1f,-1f,  0,1,
				-1f,1f,  0,0,
				1f,-1f,  1,1,
				1f,1f,  1,0
		};
		ByteBuffer vbb0 = ByteBuffer.allocateDirect(squareVertexs.length * 4);
		vbb0.order(ByteOrder.nativeOrder());
		mSqureBuffer = vbb0.asFloatBuffer();
		mSqureBuffer.put(squareVertexs);
		mSqureBuffer.position(0);


		float [] squareVertexsfbo = new float[] {
				-1f,-1f,  0,1,
				-1f,1f,  0,0,
				1f,-1f,  1,1,
				1f,1f,  1,0
		};
		ByteBuffer vbb1 = ByteBuffer.allocateDirect(squareVertexsfbo.length * 4);
		vbb1.order(ByteOrder.nativeOrder());
		mSqureBufferfbo = vbb1.asFloatBuffer();
		mSqureBufferfbo.put(squareVertexsfbo);
		mSqureBufferfbo.position(0);



	}

	public void initTexture(int res) {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), res);

		int [] textures = new int[1];
		GLES20.glGenTextures(1, textures, 0);
		mLoadedTextureId = textures[0];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mLoadedTextureId);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_MIRRORED_REPEAT);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_MIRRORED_REPEAT);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}

	public void draw(float[] mvpMatrix, float[] mMatrix) {

	    // 生成FrameBuffer
	    int [] framebuffers = new int[1];
	    GLES20.glGenFramebuffers(1, framebuffers, 0);
		// 生成Texture
		int [] textures = new int[2];
		GLES20.glGenTextures(2, textures, 0);
		//配置texture
		int colorTxtureId = textures[1];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, colorTxtureId);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_MIRRORED_REPEAT);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_MIRRORED_REPEAT);
//		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, 1024, 1024, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_SHORT_5_6_5, null);
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, 1024, 1024,0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
		if (GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER)!= GLES20.GL_FRAMEBUFFER_COMPLETE) {
			Log.e("zzzzzz", "glFramebufferTexture2D error");
		}
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,0);




		//绑定framebuffer
	    int framebufferId = framebuffers[0];
	    GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, framebufferId);
		//挂载textureID到framebufferId
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, colorTxtureId, 0);


//		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);


		int frameBufferVertexShader = loaderShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int frameBufferFagmentShader = loaderShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

		mFrameBufferProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(mFrameBufferProgram, frameBufferVertexShader);
		GLES20.glAttachShader(mFrameBufferProgram, frameBufferFagmentShader);
		GLES20.glLinkProgram(mFrameBufferProgram);

//		--
		int positionHandle1 = GLES20.glGetAttribLocation(mFrameBufferProgram, "aPosition");
		int textureCoordHandle1 = GLES20.glGetAttribLocation(mFrameBufferProgram, "aTextureCoord");
		int textureHandle1 = GLES20.glGetUniformLocation(mFrameBufferProgram, "uTexture");
		mSqureBufferfbo.position(0);
		GLES20.glVertexAttribPointer(positionHandle1, 2, GLES20.GL_FLOAT, false, (2+2) * 4, mSqureBufferfbo);
		mSqureBufferfbo.position(2);
		GLES20.glVertexAttribPointer(textureCoordHandle1, 2, GLES20.GL_FLOAT, false, (2+2) * 4, mSqureBufferfbo);
		GLES20.glEnableVertexAttribArray(positionHandle1);
		GLES20.glEnableVertexAttribArray(textureCoordHandle1);
//		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mLoadedTextureId);
		GLES20.glUniform1i(textureHandle1, 0);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER,0);




		/*================================render2window================================*/
		// 切换到窗口系统的缓冲区

		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
		int vertexShader = loaderShader(GLES20.GL_VERTEX_SHADER, windowVertexShaderCode);
		int fragmentShader = loaderShader(GLES20.GL_FRAGMENT_SHADER, windowFragmentShaderCode);
		mWindowProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(mWindowProgram, vertexShader);
		GLES20.glAttachShader(mWindowProgram, fragmentShader);
		GLES20.glLinkProgram(mWindowProgram);
		GLES20.glUseProgram(mWindowProgram);
		int positionHandle = GLES20.glGetAttribLocation(mWindowProgram, "aPosition");
		int textureCoordHandle = GLES20.glGetAttribLocation(mWindowProgram, "aTextureCoord");
		int textureHandle = GLES20.glGetUniformLocation(mWindowProgram, "uTexture");
		mSqureBuffer.position(0);
		GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, (2+2) * 4, mSqureBuffer);
		mSqureBuffer.position(2);
		GLES20.glVertexAttribPointer(textureCoordHandle, 2, GLES20.GL_FLOAT, false, (2+2) * 4, mSqureBuffer);
		GLES20.glEnableVertexAttribArray(positionHandle);
		GLES20.glEnableVertexAttribArray(textureCoordHandle);
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
	    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, colorTxtureId);
		GLES20.glUniform1i(textureHandle, 0);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

	    GLES20.glDeleteTextures(2, textures, 0);
	    GLES20.glDeleteFramebuffers(1, framebuffers, 0);
	}



	private int loaderShader(int type, String shaderCode) {
		int shader = GLES20.glCreateShader(type);
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		return shader;
	}

	private String windowVertexShaderCode = ""
			+ "attribute vec2 aPosition;"
			+ "attribute vec2 aTextureCoord;"
			+ "varying vec2 vTextureCoord;"
			+ "void main(){"
			+ "gl_Position = vec4(aPosition,0,1);"
			+ "vTextureCoord = aTextureCoord;"
			+ "}";

	private String windowFragmentShaderCode = "precision mediump float;"
			+ "uniform sampler2D uTexture;"
			+ "varying vec2 vTextureCoord;"
			+ "void main(){"
			+ "gl_FragColor = texture2D(uTexture, vTextureCoord);"
			+ "}";
	private String vertexShaderCode = ""
			+ "attribute vec2 aPosition;"
			+ "attribute vec2 aTextureCoord;"
			+ "varying vec2 vTextureCoord;"
			+ "void main(){"
			+ "gl_Position = vec4(aPosition,0,1);"
			+ "vTextureCoord = aTextureCoord;"
			+ "}";

	private String fragmentShaderCode = "precision mediump float;"
			+ "uniform sampler2D uTexture;"
			+ "varying vec2 vTextureCoord;"
			+ "void main(){"
			+ "gl_FragColor = texture2D(uTexture, vTextureCoord);"
			+ "}";


}

