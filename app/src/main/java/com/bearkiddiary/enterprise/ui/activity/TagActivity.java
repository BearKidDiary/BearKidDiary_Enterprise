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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.view.Divider;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.LayoutRipple;

public class TagActivity extends BaseActivity {

    private RecyclerView rv_tag;
    private ButtonFlat btn_add;
    private TagAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        initView();
    }

    private void initView() {
        rv_tag = (RecyclerView) findViewById(R.id.rv_tag);
        btn_add = (ButtonFlat) findViewById(R.id.btn_tag_add);

        //点击标题栏添加键
        btn_add.setOnClickListener(v -> {
            //TODO: 添加标签

        });

        //标签列表
        rv_tag.setAdapter(mAdapter = new TagAdapter());
        rv_tag.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_tag.setItemAnimator(new DefaultItemAnimator());
        rv_tag.addItemDecoration(new Divider(getContext()));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TagActivity.class));
    }

    class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
        @Override
        public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tag, parent, false);
            return new TagViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TagViewHolder holder, int position) {
            holder.lr_tag.setOnClickListener(v -> TagEditActivity.startActivity(getContext()));
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class TagViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_name, tv_size;
            private LayoutRipple lr_tag;

            public TagViewHolder(View v) {
                super(v);
                tv_name = (TextView) v.findViewById(R.id.tv_tag_name);
                tv_size = (TextView) v.findViewById(R.id.tv_tag_size);
                lr_tag = (LayoutRipple) v.findViewById(R.id.lr_tag);
            }
        }
    }
}
