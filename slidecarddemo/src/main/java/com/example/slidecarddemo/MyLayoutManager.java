package com.example.slidecarddemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chentian on 2016/12/15.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        return layoutParams;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        for(int i=0;i<getItemCount();i++){
            View child = recycler.getViewForPosition(i);
            addView(child);

            measureChildWithMargins(child,0,0);
            int width = getDecoratedMeasuredWidth(child);
            int height = getDecoratedMeasuredHeight(child);
            layoutDecorated(child,0,0,width,height);

            if(i<getItemCount()-1){
                child.setScaleX(0.8f);
                child.setScaleY(0.8f);
            }
        }
    }
}
