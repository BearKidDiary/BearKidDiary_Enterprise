package com.bearkiddiary.enterprise.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.model.QRCodeModel;
import com.bearkiddiary.enterprise.ui.activity.MyQRCodeActivity;
import com.bearkiddiary.enterprise.ui.activity.OrganizationMgmtActivity;
import com.bearkiddiary.enterprise.ui.activity.PersonalInfoActivity;
import com.bearkiddiary.enterprise.ui.activity.ResumeActivity;
import com.bearkiddiary.enterprise.ui.fragment.ifragment.IIndividualFragment;

/**
 * Created by yarenChoi on 2016/7/22.
 * 个人界面
 */
public class IndividualFragment extends BaseFragment implements IIndividualFragment, View.OnClickListener {
    private static final String TAG = "IndividualFragment";
    private static final int requestCode = 200;//扫一扫二维码的请求码

    private Context mContext;
    protected TextView nameTv;
    protected TextView phoneNumTv;
    protected ImageView avatarIv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individual, container, false);
        mContext = inflater.getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        nameTv = (TextView) view.findViewById(R.id.tv_individual_name);
        phoneNumTv = (TextView) view.findViewById(R.id.tv_individual_phonenum);
        avatarIv = (ImageView) view.findViewById(R.id.iv_individual_avatar);

        LinearLayout ll_info = (LinearLayout) view.findViewById(R.id.ll_individual_info);
        RelativeLayout rl_resume = (RelativeLayout) view.findViewById(R.id.rl_individual_resume);
        RelativeLayout rl_organization = (RelativeLayout) view.findViewById(R.id.rl_individual_organization);
        RelativeLayout rl_scan = (RelativeLayout) view.findViewById(R.id.rl_individual_scan);
        RelativeLayout rl_qr_code = (RelativeLayout) view.findViewById(R.id.rl_individual_qr_code);
        RelativeLayout rl_customer_service = (RelativeLayout) view.findViewById(R.id.rl_individual_customer_service);
        RelativeLayout rl_settings = (RelativeLayout) view.findViewById(R.id.rl_individual_settings);

        ll_info.setOnClickListener(this);
        rl_resume.setOnClickListener(this);
        rl_organization.setOnClickListener(this);
        rl_scan.setOnClickListener(this);
        rl_qr_code.setOnClickListener(this);
        rl_customer_service.setOnClickListener(this);
        rl_settings.setOnClickListener(this);

        setName("王大锤");
        setPhoneNum("15615611561");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_individual_info://个人信息
                PersonalInfoActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_resume://个人简历
                ResumeActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_organization://机构管理
                OrganizationMgmtActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_scan://扫一扫
                QRCodeModel.scanQRCode(this, requestCode);
                break;
            case R.id.rl_individual_qr_code://二维码
                MyQRCodeActivity.startActivity(mContext);
                break;
            case R.id.rl_individual_customer_service://客服中心
                break;
            case R.id.rl_individual_settings://设置
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        if (request == requestCode && result == Activity.RESULT_OK) {
            //TODO:处理扫描后获得的内容
            Bitmap QRcode = QRCodeModel.getBitmap(data);
            String content = QRCodeModel.getContent(data);
            Log.i(TAG, "扫描成功：" + content);
        } else {
            super.onActivityResult(request, result, data);
        }
    }

    @Override
    public void setName(String name) {
        nameTv.setText(name);
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        phoneNumTv.setText(phoneNum);
    }

    @Override
    public void setAvatar(int imgResource) {
        avatarIv.setImageResource(imgResource);
    }

}
