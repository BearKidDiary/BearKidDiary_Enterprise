package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.iactivity.ITagEditView;
import com.bearkiddiary.enterprise.ui.view.CircleImageview;
import com.bearkiddiary.enterprise.ui.view.IconButton;
import com.gc.materialdesign.views.ButtonFlat;

public class TagEditActivity extends BaseActivity implements ITagEditView {

    private IconButton btn_member_add, btn_member_delete;
    private ButtonFlat btn_add;
    private Button btn_delete;
    private EditText et_name;
    private RecyclerView rv_member;
    private TagMemberAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_edit);

        initView();
    }

    private void initView() {
        btn_add = (ButtonFlat) findViewById(R.id.btn_tag_add);
        btn_delete = (Button) findViewById(R.id.btn_tag_delete);
        btn_member_add = (IconButton) findViewById(R.id.btn_tag_member_add);
        btn_member_delete = (IconButton) findViewById(R.id.btn_tag_member_delete);
        et_name = (EditText) findViewById(R.id.et_tag_name);
        rv_member = (RecyclerView) findViewById(R.id.rv_tag_member);

        //点击标题栏保存键
        btn_add.setOnClickListener(v -> {
            //TODO: save the tag
            finish();
        });

        //点击删除分组键（新建分组时该按钮不可见）
        btn_delete.setOnClickListener(v -> {
            //TODO: delete the tag
        });

        //添加分组成员
        btn_member_add.setOnClickListener(v -> {
            //TODO: add member to the tag
        });

        //删除分组成员
        btn_member_delete.setOnClickListener(v -> {
            if (mAdapter.isDeleteMode())
                mAdapter.setDeleteMode(false);
            else
                mAdapter.setDeleteMode(true);
            mAdapter.notifyDataSetChanged();
        });

        //成员列表
        rv_member.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rv_member.setItemAnimator(new DefaultItemAnimator());
        rv_member.setAdapter(mAdapter = new TagMemberAdapter());
    }

    @Override
    public void notifyChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TagEditActivity.class));
    }

    class TagMemberAdapter extends RecyclerView.Adapter<TagMemberAdapter.MemberViewHolder> {

        private boolean deleteMode = false;

        public void setDeleteMode(boolean deleteMode) {
            this.deleteMode = deleteMode;
        }

        public boolean isDeleteMode() {
            return deleteMode;
        }

        @Override
        public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tag_member, parent, false);
            return new MemberViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberViewHolder holder, int position) {
            if (deleteMode) {
                holder.iv_delete.setVisibility(View.VISIBLE);
            } else {
                holder.iv_delete.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class MemberViewHolder extends RecyclerView.ViewHolder {
            private CircleImageview civ_avatar;
            private TextView tv_name;
            private ImageView iv_delete;

            public MemberViewHolder(View v) {
                super(v);
                civ_avatar = (CircleImageview) v.findViewById(R.id.civ_tag_member_avatar);
                tv_name = (TextView) v.findViewById(R.id.tv_tag_member_name);
                iv_delete = (ImageView) v.findViewById(R.id.iv_tag_member_delete);
            }
        }
    }
}
