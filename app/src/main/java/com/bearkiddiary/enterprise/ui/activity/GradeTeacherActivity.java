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
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.Divider;

public class GradeTeacherActivity extends BaseActivity {

    private TextView tv_late, tv_leave, tv_communicate, tv_upload, tv_students;
    private RecyclerView rv_teacher;
    private GradeTeacherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_teacher);

        initView();
    }

    private void initView() {
        rv_teacher = (RecyclerView) findViewById(R.id.rv_grade_teacher);
        tv_leave = (TextView) findViewById(R.id.tv_grade_leave);
        tv_late = (TextView) findViewById(R.id.tv_grade_late);
        tv_communicate = (TextView) findViewById(R.id.tv_grade_communicate);
        tv_upload = (TextView) findViewById(R.id.tv_grade_upload);
        tv_students = (TextView) findViewById(R.id.tv_grade_students);

        //教师列表
        rv_teacher.setAdapter(mAdapter = new GradeTeacherAdapter());
        rv_teacher.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_teacher.addItemDecoration(new Divider(getContext()));
        rv_teacher.setItemAnimator(new DefaultItemAnimator());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, GradeTeacherActivity.class));
    }

    class GradeTeacherAdapter extends RecyclerView.Adapter<GradeTeacherAdapter.TeacherViewHolder> {
        @Override
        public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_grade_teacher, parent, false);
            return new TeacherViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TeacherViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 12;
        }

        class TeacherViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name;

            public TeacherViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_grade_teacher_name);
            }
        }
    }
}
