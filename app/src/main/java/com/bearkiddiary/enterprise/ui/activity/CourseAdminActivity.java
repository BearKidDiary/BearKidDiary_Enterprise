package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.model.bean.Course;
import com.bearkiddiary.enterprise.model.bean.Kid;
import com.bearkiddiary.enterprise.model.bean.Teacher;
import com.bearkiddiary.enterprise.ui.activity.iactivity.ICourseView;
import com.bearkiddiary.enterprise.ui.view.CircleImageview;
import com.bearkiddiary.enterprise.ui.view.Divider;
import com.bearkiddiary.enterprise.ui.view.IconButton;

import java.util.ArrayList;
import java.util.List;

public class CourseAdminActivity extends BaseActivity implements ICourseView {

    private RecyclerView rv_course;
    private ImageView iv_back;
    private CourseAdapter mAdapter;

    private List<Kid> kids = new ArrayList<>();
    private Teacher teacher;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_admin);
        initView();
    }

    private void initView() {
        rv_course = (RecyclerView) findViewById(R.id.rv_course);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        //标题栏返回键
        iv_back.setOnClickListener(view -> finish());

        //课程对应班级信息的列表 包括任课老师、时间地点、学生名单
        rv_course.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_course.setItemAnimator(new DefaultItemAnimator());
        rv_course.setAdapter(mAdapter = new CourseAdapter(getContext()));
        rv_course.addItemDecoration(new Divider(getContext()));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CourseAdminActivity.class));
    }

    @Override
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public Course getCourse() {
        return course;
    }

    @Override
    public void updateKidList(List<Kid> list) {
        kids.addAll(list);
    }

    @Override
    public void setKidList(List<Kid> list) {
        kids = list;
    }

    @Override
    public void updateKid(Kid kid) {
        kids.add(kid);
    }

    @Override
    public void notifyChanged() {
        mAdapter.notifyDataSetChanged();
    }

    class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int HEAD = 1;
        public static final int ITEM = 2;
        private Context context;

        public CourseAdapter(Context context) {
            this.context = context;
        }

        private Context getContext() {
            return context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_course_admin_head, parent, false);
                return new HeadViewHolder(view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_kid, parent, false);
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
            return 10;
        }

        class HeadViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_name, tv_phone, tv_time, tv_location;
            private CircleImageview civ_avatar;
            private IconButton ib_changeTeacher;

            public HeadViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_course_teacher_name);
                tv_phone = (TextView) v.findViewById(R.id.tv_course_teacher_phone);
                tv_time = (TextView) v.findViewById(R.id.tv_course_time);
                tv_location = (TextView) v.findViewById(R.id.tv_course_location);
                civ_avatar = (CircleImageview) v.findViewById(R.id.civ_course_teacher_avatar);
                ib_changeTeacher = (IconButton) v.findViewById(R.id.ib_course_teacher_change);
            }
        }

        class KidViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_name;
            private CircleImageview civ_avatar;

            public KidViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_kid_name);
                civ_avatar = (CircleImageview) v.findViewById(R.id.civ_kid_avatar);
            }
        }
    }
}
