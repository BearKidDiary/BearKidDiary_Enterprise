package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bearkiddiary.enterprise.R;

/**
 * Created by yarenChoi on 2016/7/22.
 * 机构管理界面
 */
public class OrganizationMgmtActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_mgmt);
    }


    public static void startActivity(Context context){
        context.startActivity(new Intent(context, OrganizationMgmtActivity.class));
    }
}
