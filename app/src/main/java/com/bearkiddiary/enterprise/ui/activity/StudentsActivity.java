package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.adapter.StudentsAdapter;
import com.bearkiddiary.enterprise.model.bean.Students;
import com.bearkiddiary.enterprise.ui.fragment.StudentsFragment;
import com.bearkiddiary.enterprise.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YarenChoi on 2016/8/1.
 * 学生界面
 */

public class StudentsActivity extends BaseActivity {
    private static final int TABONE = 0;
    private static final int TABTWO = 1;
    private static final int TABTHREE = 2;

    protected RelativeLayout rl_tab1, rl_tab2, rl_tab3;
    protected ImageView[] iv_tab = new ImageView[3];
    private Fragment[] mFragments = new Fragment[3];
    protected int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_student).setOnClickListener(view -> finish());

        rl_tab1 = (RelativeLayout) findViewById(R.id.rl_student_tab1);
        rl_tab2 = (RelativeLayout) findViewById(R.id.rl_student_tab2);
        rl_tab3 = (RelativeLayout) findViewById(R.id.rl_student_tab3);
        rl_tab1.setOnClickListener(view -> changeTab(TABONE));
        rl_tab2.setOnClickListener(view -> changeTab(TABTWO));
        rl_tab3.setOnClickListener(view -> changeTab(TABTHREE));
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_student_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_student_tab2);
        iv_tab[TABTHREE] = (ImageView) findViewById(R.id.iv_student_tab3);

        mFragments[TABONE] = new StudentsFragment(StudentsFragment.GRADUATED);
        mFragments[TABTWO] = new StudentsFragment(StudentsFragment.ASSIGNED);
        mFragments[TABTHREE] = new StudentsFragment(StudentsFragment.UNASSIGNED);

        initContain();
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_student_contain, mFragments[TABONE]);
        transaction.commit();
    }

    private void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        iv_tab[selected].setVisibility(View.GONE);
        iv_tab[tabNum].setVisibility(View.VISIBLE);
        changeFragment(tabNum);
    }

    /**
     * 切换fragment
     * @param tabNum 目标fragment
     */
    public void changeFragment(int tabNum){
        if (mFragments[tabNum] != mFragments[selected]){
            if (mFragments[tabNum].isAdded()){
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[selected])
                        .show(mFragments[tabNum])
                        .commit();
            }else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[selected])
                        .add(R.id.activity_student_contain, mFragments[tabNum])
                        .commit();
            }
            selected = tabNum;
        }
    }


    /**
     * 为ListView填充数据
     * @param date 名字数据
     * @return 过滤后的数组
     */
    public List<Students> filledData(String [] date){
        List<Students> mSortList = new ArrayList<Students>();

        for(int i=0; i<date.length; i++){
            Students sortModel = new Students();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0,1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setPingyin(sortString.toUpperCase());
            }else{
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
    public void filterData(String filterStr, List<Students> sourceDateList, StudentsAdapter studentsAdapter){
        List<Students> filterDateList = new ArrayList<>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = sourceDateList;
        }else{
            filterDateList.clear();
            for(Students sortModel : sourceDateList){
                String name = sortModel.getName();
                if(name.contains(filterStr) || PinyinUtils.getPingYin(name).startsWith(filterStr)){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList);
        studentsAdapter.refreshData(filterDateList);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, StudentsActivity.class));
    }
}
