package com.opengles.nativeglesview.util;

import android.content.Context;

import com.opengles.nativeglesview.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author action.zhou
 * @version v1.0
 * @date Created in 2019/4/8
 * @description
 */
public class ShaderUtil {
    private ShaderUtil(){}

    public  static String getVShaderSource(Context context,ShaderSoucreType type){
        int currentType;
        switch (type){
                case NONE:
                    currentType=  R.raw.vshader;
                default:
                    currentType= R.raw.vshader;
        }
        return LoadShaderStr(context,currentType);
    }

    public  static String getFShaderSource(Context context,ShaderSoucreType type){
        int currentType;
        switch (type){
            case NONE:
                currentType=  R.raw.fshader;
            default:
                currentType= R.raw.fshader;
        }
        return LoadShaderStr(context,currentType);
    }

    public static  String LoadShaderStr(Context context,int resId){
        StringBuffer strBuf = new StringBuffer();
        try {
            InputStream inputStream = context.getResources().openRawResource(resId);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String read = in.readLine();
            while (read != null) {
                strBuf.append(read + "\n");
                read = in.readLine();
            }
            strBuf.deleteCharAt(strBuf.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuf.toString();
    }

}
