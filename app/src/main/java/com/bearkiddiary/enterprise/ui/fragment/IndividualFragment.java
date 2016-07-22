package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.OrganizationMgmtActivity;
import com.bearkiddiary.enterprise.ui.activity.PersonalInfoActivity;

/**
 * Created by yarenChoi on 2016/7/22.
 * 个人界面
 */
public class IndividualFragment extends BaseFragment implements View.OnClickListener {
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        LinearLayout ll_info = (LinearLayout) view.findViewById(R.id.ll_individual_info);
        RelativeLayout rl_resume = (RelativeLayout) view.findViewById(R.id.rl_individual_resume);
        RelativeLayout rl_organization = (RelativeLayout) view.findViewById(R.id.rl_individual_organization);
        RelativeLayout rl_customer_service = (RelativeLayout) view.findViewById(R.id.rl_individual_customer_service);
        RelativeLayout rl_settings = (RelativeLayout) view.findViewById(R.id.rl_individual_settings);
        RelativeLayout rl_qr_code = (RelativeLayout) view.findViewById(R.id.rl_individual_qr_code);

        ll_info.setOnClickListener(this);
        rl_resume.setOnClickListener(this);
        rl_organization.setOnClickListener(this);
        rl_customer_service.setOnClickListener(this);
        rl_settings.setOnClickListener(this);
        rl_qr_code.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_individual_info:
                PersonalInfoActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_resume:
                break;
            case R.id.rl_individual_organization:
                OrganizationMgmtActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_customer_service:
                break;
            case R.id.rl_individual_settings:
                break;
            case R.id.rl_individual_qr_code:
                break;
            default:
                break;
        }
    }
}
