package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.IconButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarenChoi on 2016/7/22.
 * 机构管理界面
 */
public class OrganizationMgmtActivity extends BaseActivity {
    private PopupMenu popupMenu;
    private String[] menuItems = new String[]{"创建", "加入"};
    private List<String> orgName = new ArrayList<>();
    private List<String> orgJob = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_mgmt);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.iv_title_back_organization_mgmt);
        IconButton addBtn = (IconButton) findViewById(R.id.ibtn_organization_mgmt);
        RecyclerView rvOrganization = (RecyclerView) findViewById(R.id.rv_organization);

        back.setOnClickListener(view -> finish());

        addBtn.setOnClickListener(view -> popupMenu.show());
        popupMenu = new PopupMenu(getContext(), addBtn);
        for (int i = 0; i < menuItems.length; i++)
            popupMenu.getMenu().add(menuItems[i]);
        popupMenu.setOnMenuItemClickListener(item -> {
            String title = item.getTitle().toString();
            if (title.equals(menuItems[0])) {
                //创建
            } else if (title.equals(menuItems[1])) {
                //加入
            }
            return true;
        });


        orgName.add("新东方");
        orgJob.add("数学老师");
        orgName.add("春田花花幼稚园");
        orgJob.add("管理员");
        rvOrganization.setLayoutManager(new LinearLayoutManager(OrganizationMgmtActivity.this));
        rvOrganization.setItemAnimator(new DefaultItemAnimator());
        rvOrganization.setAdapter(new RecyclerViewAdapter());
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    OrganizationMgmtActivity.this).inflate(R.layout.item_organization_mgmt, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.root.setTag(position);
            holder.root.setOnClickListener(v -> {
                OrganizationInfoActivity.startActivity(OrganizationMgmtActivity.this);//v.getTag()
            });
            holder.name.setText(orgName.get(position));
            holder.job.setText(orgJob.get(position));
        }

        @Override
        public int getItemCount() {
            return orgName.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout root;
            ImageView avatar;
            TextView name;
            TextView job;

            public MyViewHolder(View view) {
                super(view);
                root = (LinearLayout) view.findViewById(R.id.item_organization_mgmt_root);
                avatar = (ImageView) view.findViewById(R.id.item_organization_mgmt_avatar);
                name = (TextView) view.findViewById(R.id.item_organization_mgmt_name);
                job = (TextView) view.findViewById(R.id.item_organization_mgmt_job);
            }
        }
    }


    public static void startActivity(Context context){
        context.startActivity(new Intent(context, OrganizationMgmtActivity.class));
    }
}
