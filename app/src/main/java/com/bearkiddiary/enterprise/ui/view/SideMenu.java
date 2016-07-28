package com.bearkiddiary.enterprise.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2016/7/27.
 * 侧滑隐藏的菜单
 */
public class SideMenu extends RelativeLayout {
    public SideMenu(Context context) {
        super(context);
        init(context);
    }

    public SideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SideMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private View menu;
    private List<View> menuItem = new ArrayList<>();

    protected void init(Context context) {
    }

    private boolean trigger = false;
    private float pressX, pressY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            pressX = event.getX();
            pressY = event.getY();
            trigger = true;
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //若水平方向上移动较大 则拦截事件进行处理
            if (Math.abs(event.getX() - pressX) > Math.abs(event.getY() - pressY))
                return true;
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("zy", "onTouchEvent: " + event.getAction());
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (pressX - event.getX() > 100 && trigger) {
                trigger = false;
                openMenu();
            } else if (event.getX() - pressX > 100 && trigger) {
                trigger = false;
                closeMenu();
            }
            return false;
        }
        return super.onTouchEvent(event);
    }

    private boolean isOpen = true;

    public void openMenu() {
        if (menu != null) {
            int width = menu.getWidth();
            for (int i = 0; i < menuItem.size(); i++) {
                View child = menuItem.get(i);
                if (child.getTranslationX() < width) return;//如果菜单已经在打开的状态或正在运动的状态 不播放动画
                postDelayed(() -> ObjectAnimator.ofFloat(child, "translationX", width, -width / 8f, 0).setDuration(500).start(), i * 50);
            }
            isOpen = true;
        }
    }

    public void closeMenu() {
        if (menu != null) {
            for (int i = 0; i < menuItem.size(); i++) {
                int width = menu.getWidth();
                View child = menuItem.get(i);
                if (child.getTranslationX() > 0) return;//如果菜单已经在关闭的状态或正在运动的状态 不播放动画
                post(() -> ObjectAnimator.ofFloat(child, "translationX", 0, width).setDuration(500).start());
            }
            isOpen = false;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setSideMenuResourse(int resId) {
        menu = LayoutInflater.from(getContext()).inflate(resId, SideMenu.this, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(ALIGN_PARENT_RIGHT);
        addView(menu, lp);

        if (menu instanceof ViewGroup) {
            ViewGroup menuGroup = (ViewGroup) menu;
            int cnt = menuGroup.getChildCount();
            for (int i = 0; i < cnt; i++) {
                View child = menuGroup.getChildAt(i);
                menuItem.add(child);
                final int pos = i;
                if (itemListener != null) {
                    child.setOnClickListener(view -> itemListener.onClick(view, pos));
                }
            }
        }
    }

    private OnClickMenuItemListener itemListener;

    public void setOnClickMenuItemListener(OnClickMenuItemListener listener) {
        itemListener = listener;

        for (int i = 0; i < menuItem.size(); i++) {
            View child = menuItem.get(i);
            final int pos = i;
            if (itemListener != null) {
                child.setOnClickListener(view -> itemListener.onClick(view, pos));
            }
        }
    }

    public interface OnClickMenuItemListener {
        void onClick(View v, int postion);
    }

//    public interface onMenuListener {
//        void onOpen();
//
//        void onClose();
//    }
}
