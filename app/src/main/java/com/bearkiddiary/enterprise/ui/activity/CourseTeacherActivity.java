package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.CircleImageview;
import com.bearkiddiary.enterprise.ui.view.IconButton;
import com.bearkiddiary.enterprise.ui.view.SideMenu;

public class CourseTeacherActivity extends BaseActivity {

    private SideMenu sideMenu;
    private RecyclerView rv_course;
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
        ib_menu = (IconButton) findViewById(R.id.ib_course_menu);

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

        //侧滑菜单的资源设置
        sideMenu.setSideMenuResourse(R.layout.item_course_side_menu);
        sideMenu.setOnClickMenuItemListener((v, postion) -> {
            switch (v.getId()) {
                case R.id.btn_course_roll_call://点名
                    RollCallActivity.startActivity(getContext());
                    break;
                case R.id.btn_course_mass://群发
                    SendMsgActivity.startActivity(getContext(), SendMsgActivity.MASS);
                    break;
                case R.id.btn_course_add://添加学生
                    SendMsgActivity.startActivity(getContext(), SendMsgActivity.ADD_STUDENT);
                    break;
                case R.id.btn_course_grade://评分
                    GradeKidActivity.startActivity(getContext());
                    break;
                default:
                    break;
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CourseTeacherActivity.class));
    }

    class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int HEAD = 1;
        public static final int ITEM = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_organization_dynamic, parent, false);
                return new HeadViewHolder(view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_course_kid, parent, false);
                return new KidViewHolder(view);
            }
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
            return 30;
        }

        class HeadViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_dynamic;

            public HeadViewHolder(View v) {
                super(v);
                tv_dynamic = (TextView) v.findViewById(R.id.tv_course_dynamic);
            }
        }

        class KidViewHolder extends RecyclerView.ViewHolder {
            private CircleImageview civ_avatar;
            private TextView tv_name;

            public KidViewHolder(View v) {
                super(v);
                civ_avatar = (CircleImageview) v.findViewById(R.id.civ_kid_avatar);
                tv_name = (TextView) v.findViewById(R.id.tv_kid_name);
            }
        }
    }
}
