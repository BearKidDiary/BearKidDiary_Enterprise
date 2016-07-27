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
        menu = LayoutInflater.from(context).inflate(R.layout.item_side_menu, SideMenu.this, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(ALIGN_PARENT_RIGHT);
        addView(menu, lp);

        if (menu instanceof ViewGroup) {
            ViewGroup menuGroup = (ViewGroup) menu;
            int cnt = menuGroup.getChildCount();
            for (int i = 0; i < cnt; i++) {
                View child = menuGroup.getChildAt(i);
                menuItem.add(child);
                final int pos = i;
                child.setOnClickListener(view -> {
                    if (itemListener != null) itemListener.onClick(view, pos);
                });
            }
        }
    }

    private boolean trigger = false;
    private float pressX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            trigger = true;
            pressX = event.getX();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (pressX - event.getX() > 100 && trigger) {
                trigger = false;
                openMenu();
            } else if (event.getX() - pressX > 100 && trigger) {
                trigger = false;
                closeMenu();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void openMenu() {
        if (menu != null) {
            //if (menu.getTranslationX() == 0) return;

            int width = menu.getWidth();
            for (int i = 0; i < menuItem.size(); i++) {
                View child = menuItem.get(i);
                postDelayed(() -> ObjectAnimator.ofFloat(child, "translationX", -width / 3f, width, 0).setDuration(500).start(), i * 100);
            }
        }
    }

    public void closeMenu() {
        if (menu != null) {
            //if (menu.getTranslationX() == 0)
            for (int i = 0; i < menuItem.size(); i++) {
                int width = menu.getWidth();
                View child = menuItem.get(i);
                post(() -> ObjectAnimator.ofFloat(child, "translationX", 0, width).setDuration(500).start());
                //postDelayed(() -> ObjectAnimator.ofFloat(child, "translationX", -width / 3f, width, 0).setDuration(500).start(), i * 100);
            }
//            post(() -> {
//                    int width = menu.getWidth();
//                    ObjectAnimator.ofFloat(menu, "translationX", 0, width).setDuration(500).start();
//                });
        }
    }

    private OnClickMenuItemListener itemListener;

    public void setOnClickMenuItemListener(OnClickMenuItemListener listener) {
        itemListener = listener;
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
