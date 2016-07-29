package com.bearkiddiary.enterprise.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author zy
 *         recyclerView的分割线
 */
public class Divider extends RecyclerView.ItemDecoration {
    Paint paint = new Paint();

    public Divider(Context context) {
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int cnt = parent.getChildCount();

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < cnt; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = top + 1;
            c.drawRect(left, top, right, bottom, paint);
        }
    }
}