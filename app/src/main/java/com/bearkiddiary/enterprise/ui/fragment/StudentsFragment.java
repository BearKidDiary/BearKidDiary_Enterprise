package com.bearkiddiary.enterprise.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.adapter.StudentsAdapter;
import com.bearkiddiary.enterprise.model.bean.Students;
import com.bearkiddiary.enterprise.ui.view.ClearEditText;
import com.bearkiddiary.enterprise.ui.view.SideBar;
import com.bearkiddiary.enterprise.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    protected List<Students> studentsList;//联系人列表

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
                filterData(s.toString());
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
        studentsList = filledData(getResources().getStringArray(R.array.names));
        adapter.refreshData(studentsList);
    }


    /**
     * 为ListView填充数据
     * @param date 名字数据
     * @return 过滤后的数组
     */
    private List<Students> filledData(String [] date){
        List<Students> mSortList = new ArrayList<>();

        for (String aDate : date) {
            Students sortModel = new Students();
            sortModel.setName(aDate);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(aDate);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setPingyin(sortString.toUpperCase());
            } else {
                sortModel.setPingyin("#");
            }

            mSortList.add(sortModel);
        }
        Collections.sort(mSortList);
        return mSortList;

    }


    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr 输入框中的字符串
     */
    private void filterData(String filterStr){
        List<Students> filterDateList = new ArrayList<>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = studentsList;
        }else{
            filterDateList.clear();
            for(Students sortModel : studentsList){
                String name = sortModel.getName();
                if(name.contains(filterStr) || PinyinUtils.getPingYin(name).startsWith(filterStr)){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList);
        adapter.refreshData(filterDateList);
    }

}
