package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.IconButton;
import com.bearkiddiary.enterprise.ui.view.SideMenu;

public class CourseTeacherActivity extends BaseActivity {

    private SideMenu sideMenu;
    private RecyclerView rv_course;
    private ImageView iv_back;
    private IconButton ib_menu;
    private CourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_teacher);

        initView();
    }

    private void initView() {
        sideMenu = (SideMenu) findViewById(R.id.sidemenu_course);
        rv_course = (RecyclerView) findViewById(R.id.rv_course);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ib_menu = (IconButton) findViewById(R.id.ib_course_menu);

        //点击标题栏返回键
        iv_back.setOnClickListener(v -> finish());

        //点击标题栏菜单键
        ib_menu.setOnClickListener(v -> {
            if (sideMenu.isOpen()) sideMenu.closeMenu();
            sideMenu.openMenu();
        });

        //学生列表
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) return 4;
                return 1;
            }
        });
        rv_course.setLayoutManager(manager);
        rv_course.setAdapter(mAdapter = new CourseAdapter());
        rv_course.setItemAnimator(new DefaultItemAnimator());
        rv_course.addItemDecoration(new Divider(getContext()));

        //侧滑菜单的资源设置
        sideMenu.setSideMenuResourse(R.layout.item_course_side_menu);
        sideMenu.setOnClickMenuItemListener((v, postion) -> {

        });
    }

    class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int HEAD = 1;
        public static final int ITEM = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return HEAD;
            return ITEM;
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class HeadViewHolder extends RecyclerView.ViewHolder{

            public HeadViewHolder(View v) {
                super(v);
            }
        }

        class KidViewHolder extends RecyclerView.ViewHolder{
            public KidViewHolder(View v) {
                super(v);
            }
        }
    }

    static class Divider extends RecyclerView.ItemDecoration {
        Paint paint = new Paint();

        public Divider(Context context) {
            paint.setColor(Color.DKGRAY);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int cnt = parent.getChildCount();

            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            for (int i = 0; i < cnt; i++) {
                View child = parent.getChildAt(i);
                int pos = parent.getChildAdapterPosition(child);
                if (pos != 0) {
                    int top = child.getBottom();
                    int bottom = top + 1;
                    c.drawRect(left, top, right, bottom, paint);
                }
            }
        }
    }
}
