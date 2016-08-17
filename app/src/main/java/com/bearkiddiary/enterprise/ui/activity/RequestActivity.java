package com.bearkiddiary.enterprise.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;

import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends BaseActivity implements View.OnClickListener{
    private static final int TABONE = 0;
    private static final int TABTWO = 1;

    protected ImageView[] iv_tab = new ImageView[2];
    protected int selected;

    protected LinearLayout ll_module;
    protected ListView lv_request;

    public List<String> requestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        initView();
        initContain();
    }

    private void initView() {
        findViewById(R.id.iv_title_back_request).setOnClickListener(this);
        findViewById(R.id.rl_request_tab1).setOnClickListener(this);
        findViewById(R.id.rl_request_tab2).setOnClickListener(this);
        iv_tab[TABONE] = (ImageView) findViewById(R.id.iv_request_tab1);
        iv_tab[TABTWO] = (ImageView) findViewById(R.id.iv_request_tab2);

        ll_module = (LinearLayout) findViewById(R.id.ll_request_module);
        lv_request = (ListView) findViewById(R.id.lv_my_request);
        lv_request.setAdapter(new RequestAdapter());

        findViewById(R.id.tv_request_module_1).setOnClickListener(this);
        findViewById(R.id.tv_request_module_2).setOnClickListener(this);
        findViewById(R.id.tv_request_module_3).setOnClickListener(this);
        findViewById(R.id.tv_request_module_4).setOnClickListener(this);
        findViewById(R.id.tv_request_module_5).setOnClickListener(this);
        findViewById(R.id.tv_request_module_6).setOnClickListener(this);
        findViewById(R.id.tv_request_module_7).setOnClickListener(this);
        findViewById(R.id.tv_request_module_8).setOnClickListener(this);
    }

    private void initContain() {
        selected = TABONE;
        iv_tab[selected].setVisibility(View.VISIBLE);
    }

    private void changeTab(int tabNum) {
        if (tabNum == selected) {
            return;
        }
        iv_tab[selected].setVisibility(View.GONE);
        iv_tab[tabNum].setVisibility(View.VISIBLE);
        switch (tabNum) {
            case TABONE:
                ll_module.setVisibility(View.VISIBLE);
                lv_request.setVisibility(View.GONE);
                break;
            case TABTWO:
                ll_module.setVisibility(View.GONE);
                lv_request.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back_request:
                finish();
                break;
            case R.id.rl_request_tab1:
                changeTab(TABONE);
                break;
            case R.id.rl_request_tab2:
                changeTab(TABTWO);
                break;
            case R.id.tv_request_module_1://请假
                AskForLeaveActivity.startActivity(RequestActivity.this);
                break;
            case R.id.tv_request_module_2://新设课程
                break;
            case R.id.tv_request_module_3://报销
                break;
            case R.id.tv_request_module_4://立项申请
                break;
            case R.id.tv_request_module_5://调课报备
                break;
            case R.id.tv_request_module_6://绩效自评
                break;
            case R.id.tv_request_module_7://离职
                break;
            case R.id.tv_request_module_8://通用审批
                break;
            default:
                break;
        }
    }

    class RequestAdapter extends BaseAdapter {
        private static final String REQUEST = "申请";
        private static final String REASON = "原因：";

        @Override
        public int getCount() {
            return requestList.size();
        }

        @Override
        public Object getItem(int position) {
            return requestList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(RequestActivity.this).inflate(R.layout.item_request,null);
                holder = new ViewHolder();
                holder.type = (TextView) convertView.findViewById(R.id.tv_request_type);
                holder.status = (TextView) convertView.findViewById(R.id.tv_request_status);
                holder.desc = (TextView) convertView.findViewById(R.id.tv_request_desc);
                holder.time = (TextView) convertView.findViewById(R.id.tv_request_time);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.type.setText("请假" + REQUEST);
            holder.status.setText("未审批");
            holder.desc.setText(REASON + "原因");
            holder.time.setText("2015-02-20");
            return convertView;
        }

        class ViewHolder {
            TextView type;
            TextView status;
            TextView desc;
            TextView time;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, RequestActivity.class));
    }
}
