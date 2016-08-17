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
import com.bearkiddiary.enterprise.adapter.ContactsAdapter;
import com.bearkiddiary.enterprise.model.bean.Contact;
import com.bearkiddiary.enterprise.ui.activity.StudentInfoActivity;
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
public class ContactsFragment extends BaseFragment {

    public static final int GRADUATED = 0;//已毕业学生
    public static final int ASSIGNED = 1;//已有班级学生
    public static final int UNASSIGNED = 2;//未有班级学生
    public static final int COLLEAGUE = 3;//同事
    public static final int PARENTS = 4;//学生家长

    protected int type;
    private static final String TYPE = "contactsType";

    private Context mContext;
    protected ClearEditText mClearEditText;//搜索框
    protected SideBar sideBar;//联系人的字母标识栏
    protected RecyclerView rv_contacts;
    protected TextView tv_contacts_dialog;
    protected List<Contact> contactsList;//联系人列表

    public ContactsAdapter adapter;

    public static ContactsFragment newInstance(int type) {
        ContactsFragment contactsFragment = new ContactsFragment();
        Bundle fragmentArgs = new Bundle();
        fragmentArgs.putInt(TYPE, type);
        contactsFragment.setArguments(fragmentArgs);
        return contactsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mContext = inflater.getContext();
        type = getArguments().getInt(TYPE, GRADUATED);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sideBar = (SideBar) view.findViewById(R.id.sideBar_contacts);
        mClearEditText = (ClearEditText) view.findViewById(R.id.et_filter);
        tv_contacts_dialog = (TextView) view.findViewById(R.id.tv_contacts_dialog);
        rv_contacts = (RecyclerView) view.findViewById(R.id.rv_contacts);

        sideBar.setTextView(tv_contacts_dialog);
        //设置SideBar监听
        sideBar.setOnTouchingLetterChangedListener(s -> {
            //该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            rv_contacts.scrollToPosition(position);
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

        adapter = new ContactsAdapter(mContext);
        setOnItemClickListener();
        //学生列表
        rv_contacts.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_contacts.setItemAnimator(new DefaultItemAnimator());
        rv_contacts.setAdapter(adapter);

        //获取到联系人列表，String[] names
        contactsList = filledData(getResources().getStringArray(R.array.names));
        adapter.refreshData(contactsList);
    }

    private void setOnItemClickListener() {
        switch (type) {
            case GRADUATED:
            case ASSIGNED:
            case UNASSIGNED:
                adapter.setOnItemClickListener(v -> StudentInfoActivity.startActivity(mContext));
                break;
            default:
                break;
        }
    }


    /**
     * 为ListView填充数据
     * @param date 名字数据
     * @return 过滤后的数组
     */
    private List<Contact> filledData(String [] date){
        List<Contact> mSortList = new ArrayList<>();

        for (String aDate : date) {
            Contact sortModel = new Contact();
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
        List<Contact> filterDateList = new ArrayList<>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = contactsList;
        }else{
            filterDateList.clear();
            for(Contact sortModel : contactsList){
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
