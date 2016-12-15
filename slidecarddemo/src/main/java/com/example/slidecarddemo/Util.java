package com.example.slidecarddemo;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by chentian on 2016/12/13.
 */

public class Util {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static double distance(float x1,float y1,float x2,float y2){
        double dis = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return dis;
    }

    public static boolean isInCircle(float x, float y, float circleX,float circleY,float radius){
        if(distance(x,y,circleX,circleY)>radius){
            return false;
        } else {
            return true;
        }
    }

    public static int getScreenWidth(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
