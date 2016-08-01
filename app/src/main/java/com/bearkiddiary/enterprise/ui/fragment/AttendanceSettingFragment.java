package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.WorkAttendanceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarenChoi on 2016/7/28.
 * 考勤设置界面
 */
public class AttendanceSettingFragment extends BaseFragment {
    private Context mContext;
    public List<AttendanceGroup> attendanceGroups = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance_setting, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.rl_attendance_add_group).setOnClickListener(view1 ->
                ((WorkAttendanceActivity)getActivity()).changeTab(WorkAttendanceActivity.TABTHREE));

        RecyclerView rv_attendance = (RecyclerView) view.findViewById(R.id.rv_attendance);

        rv_attendance.setLayoutManager(new LinearLayoutManager(mContext));
        rv_attendance.setItemAnimator(new DefaultItemAnimator());
        rv_attendance.setAdapter(new RecyclerViewAdapter());
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.item_attendance, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
//            holder.root.setTag(position);
//            holder.root.setOnClickListener(v -> {
//                //v.getTag()
//            });
//            holder.name.setText(orgName.get(position));
//            holder.job.setText(orgJob.get(position));
        }

        @Override
        public int getItemCount() {
            return attendanceGroups.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
//            LinearLayout root;
//            ImageView avatar;
//            TextView name;
//            TextView job;

            public MyViewHolder(View view) {
                super(view);
//                root = (LinearLayout) view.findViewById(R.id.item_organization_mgmt_root);
            }
        }
    }

    class AttendanceGroup {
        String type; //组别
        int count; //人数
    }

}
