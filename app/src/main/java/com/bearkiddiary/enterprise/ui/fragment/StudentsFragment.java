package com.bearkiddiary.enterprise.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.adapter.StudentsAdapter;
import com.bearkiddiary.enterprise.model.bean.Students;
import com.bearkiddiary.enterprise.ui.activity.StudentsActivity;
import com.bearkiddiary.enterprise.ui.view.ClearEditText;
import com.bearkiddiary.enterprise.ui.view.SideBar;

import java.util.List;

/**
 * Created by YarenChoi on 2016/8/5.
 * 已毕业学生界面
 */

@SuppressLint("ValidFragment")
public class StudentsFragment extends BaseFragment {
    public static final int GRADUATED = 0;
    public static final int ASSIGNED = 1;
    public static final int UNASSIGNED = 2;
    protected int type;

    private Context mContext;
    protected ClearEditText mClearEditText;//搜索框
    protected SideBar sideBar;//联系人的字母标识栏
    protected RecyclerView rv_students;
    protected TextView tv_students_dialog;
    private List<Students> studentsList;//联系人列表

    public StudentsAdapter adapter;

    public StudentsFragment(int type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        sideBar = (SideBar) view.findViewById(R.id.sideBar_students);
        mClearEditText = (ClearEditText) view.findViewById(R.id.et_filter);
        tv_students_dialog = (TextView) view.findViewById(R.id.tv_students_dialog);
        rv_students = (RecyclerView) view.findViewById(R.id.rv_students);

        sideBar.setTextView(tv_students_dialog);
        //设置SideBar监听
        sideBar.setOnTouchingLetterChangedListener(s -> {
            //该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            rv_students.scrollToPosition(position);
        });

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                ((StudentsActivity)getActivity()).filterData(s.toString(), studentsList, adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        adapter = new StudentsAdapter(mContext);
        //学生列表
        rv_students.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_students.setItemAnimator(new DefaultItemAnimator());
        rv_students.setAdapter(adapter);

        //获取到联系人列表，String[] names
        studentsList = ((StudentsActivity)getActivity()).filledData(getResources().getStringArray(R.array.names));
        adapter.refreshData(studentsList);
    }

}
