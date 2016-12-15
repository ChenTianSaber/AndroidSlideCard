package com.example.slidecarddemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by chentian on 2016/12/15.
 */

public class MyRecyclerView extends RecyclerView {

    int itemX = 0;
    int itemY = 0;

    int touchDownX = 0;
    int touchDownY = 0;

    int moveX = 0;
    int moveY = 0;

    int dx = 0;
    int dy = 0;

    Context mContext;

    public MyRecyclerView(Context context) {
        this(context,null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (getChildCount() == 0) {
            return super.onTouchEvent(e);
        }
        View view = getChildAt(getChildCount() - 1);
        CardView topView = (CardView) view.findViewById(R.id.card_view);
        CardView nextView = null;

        if(getChildAt(getChildCount() - 2)!=null){
            View view2 = getChildAt(getChildCount() - 2);
            nextView = (CardView) view2.findViewById(R.id.card_view);
        }


        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchDownX = (int) e.getX();
                touchDownY = (int) e.getY();

                itemX = (int) topView.getX();
                itemY = (int) topView.getY();

                break;

            case MotionEvent.ACTION_MOVE:
                moveX = (int) e.getX();
                moveY = (int) e.getY();

                dx = moveX-touchDownX;
                dy = moveY-touchDownY;

                topView.setX(itemX+dx);
                topView.setY(itemY+dy);

                if(nextView!=null){
                    ViewGroup.LayoutParams params = topView.getLayoutParams();
                    int width = params.width/6;
                    float fraction = calcuFraction(Math.abs(dx),Math.abs(dy),width);
                    NextItemBig(nextView,fraction);
                }
                break;

            case MotionEvent.ACTION_UP:
                ViewGroup.LayoutParams params = topView.getLayoutParams();
                int width = params.width/6;
                int height = params.height/6;
                Log.d("TAG", "width :"+width+'\n'+"height :"+height);
                if(isOut(width,height)){
                    //Toast.makeText(mContext, "控件移出了", Toast.LENGTH_SHORT).show();
                    OutAnimation();
                }else {
                    //Toast.makeText(mContext, "控件未移出", Toast.LENGTH_SHORT).show();
                    topView.setX(itemX);
                    topView.setY(itemY);
                    BackAnimation(topView);
                }

                break;
        }

        return super.onTouchEvent(e);
    }

    private void OutAnimation() {
        MyAdapter adapter = (MyAdapter) this.getAdapter();
        adapter.delTopItem();
    }

    private void BackAnimation(View view){
        int centerX = Util.getScreenWidth(mContext)/2;
        int centerY = Util.getScreenHeight(mContext)/2;

        int curX = (int) (view.getX()+Util.dip2px(mContext,100));
        int curY = (int) (view.getY()+Util.dip2px(mContext,100));

        shakeAnimation(1,centerX,centerY,curX,curY);
    }

    public void shakeAnimation(int counts,int centerX,int centerY,int curX,int curY) {
        Animation translateAnimation = new TranslateAnimation((centerX - curX) / 2, 0, (centerY - curY) / 2, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(200);
        startAnimation(translateAnimation);
    }

    private void NextItemBig(View view,float fraction) {
        view.setScaleX(fraction);
        view.setScaleY(fraction);
    }

    float calcuFraction(int dx,int dy,int width){
        int distance = (int) Math.sqrt(dx*dx+dy*dy);
        float fraction = 1+0.25f *(distance/width);
        if(fraction>=1.25f){
            return 1.25f;
        }else if(fraction<=1){
            return 1;
        }
        else {
            return fraction;
        }
    }

    public boolean isOut(int width,int height){
        if(Math.abs(dx)>Util.dip2px(mContext,width)||Math.abs(dy)>Util.dip2px(mContext,height)){
            return true;
        }else {
            return false;
        }
    }
}
