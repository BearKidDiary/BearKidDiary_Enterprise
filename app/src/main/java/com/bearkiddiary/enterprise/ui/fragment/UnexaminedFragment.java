package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

/**
 * Created by YarenChoi on 2016/8/1.
 * 待审批的界面
 */
public class UnexaminedFragment extends BaseFragment {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unexamined, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView rv_unexamined = (RecyclerView) view.findViewById(R.id.rv_unexamined);

        rv_unexamined.setLayoutManager(new LinearLayoutManager(mContext));
        rv_unexamined.setItemAnimator(new DefaultItemAnimator());
        rv_unexamined.setAdapter(new RecyclerViewAdapter());
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.item_unexamined, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.root.setTag(position);
            holder.root.setOnClickListener(v -> {
                //OrganizationInfoActivity.startActivity(mContext);//v.getTag()
            });
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout root;
            TextView tv_app_type;
            TextView tv_desc;
            TextView tv_time;

            public MyViewHolder(View view) {
                super(view);
                root = (LinearLayout) view.findViewById(R.id.item_unexamined_root);
                tv_app_type = (TextView) view.findViewById(R.id.tv_unexamined_application_type);
                tv_desc = (TextView) view.findViewById(R.id.tv_unexamined_desc);
                tv_time = (TextView) view.findViewById(R.id.tv_unexamined_time);
            }
        }
    }
}
