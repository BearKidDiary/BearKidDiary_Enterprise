package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.Divider;
import com.bearkiddiary.enterprise.ui.view.IconButton;
import com.bearkiddiary.enterprise.ui.view.SideMenu;

public class StaffActivity extends BaseActivity {

    private RecyclerView rv_staff;
    private IconButton ib_menu;
    private ImageView iv_back;
    private SideMenu sideMenu;
    private StaffAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        initView();
    }

    private void initView() {
        rv_staff = (RecyclerView) findViewById(R.id.rv_staff);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ib_menu = (IconButton) findViewById(R.id.ib_stuff_menu);
        sideMenu = (SideMenu) findViewById(R.id.sidemenu_stuff);

        //点击标题栏返回键
        iv_back.setOnClickListener(view -> finish());

        //点击标题栏菜单键
        ib_menu.setOnClickListener(view -> {
            if (sideMenu.isOpen()) sideMenu.closeMenu();
            else sideMenu.openMenu();
        });

        //员工列表
        rv_staff.setItemAnimator(new DefaultItemAnimator());
        rv_staff.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_staff.addItemDecoration(new Divider(getContext()));
        rv_staff.setAdapter(mAdapter = new StaffAdapter());

        //添加侧滑菜单的资源文件
        sideMenu.setSideMenuResourse(R.layout.item_staff_side_menu);
        sideMenu.setOnClickMenuItemListener((v, postion) -> {
            switch (v.getId()) {
                case R.id.btn_staff_add://添加员工
                    AddEmployeeActivity.startActivity(StaffActivity.this);
                    break;
                case R.id.btn_staff_grade://等级评价
                    GradeTeacherActivity.startActivity(getContext());
                    break;
                case R.id.btn_staff_tag://分组管理
                    TagActivity.startActivity(getContext());
                    break;
                default:
                    break;
            }
        });
    }

    public static final void startActivity(Context context) {
        context.startActivity(new Intent(context, StaffActivity.class));
    }

    class StaffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int HEAD = 1;
        public static final int ITEM = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEAD) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_organization_dynamic, parent, false);
                return new OrgViewHolder(view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
                return new StaffViewHolder(view);
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
            return 8;
        }

        class OrgViewHolder extends RecyclerView.ViewHolder {
            public OrgViewHolder(View v) {
                super(v);
            }
        }

        class StaffViewHolder extends RecyclerView.ViewHolder {
            public StaffViewHolder(View v) {
                super(v);
            }
        }
    }
}
