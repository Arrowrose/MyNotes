package com.example.stelp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NonSwipeableBottomNavigationView extends BottomNavigationView {

    public NonSwipeableBottomNavigationView(Context context) {
        super(context);
    }

    public NonSwipeableBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonSwipeableBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public  boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        return  false;
    }
}
