package com.bearkiddiary.enterprise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.model.QRCodeModel;
import com.bearkiddiary.enterprise.utils.ScreenMetricsUtil;

public class MyQRCodeActivity extends BaseActivity {
    protected ImageView avatar;
    protected ImageView qrcode;
    protected Bitmap bitmapQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrcode);
        initView();
    }

    private void initView() {
        avatar = (ImageView) findViewById(R.id.iv_qrcode_avatar);
        qrcode = (ImageView) findViewById(R.id.iv_my_qrcode);
        int screenWidth = ScreenMetricsUtil.getScreenMetrics(this).widthPixels;
        int space = (int) ScreenMetricsUtil.dp2px(this, 20f);
        int width = (screenWidth - space);
        bitmapQRCode = QRCodeModel.createQRCode("二维码包含的信息", width, width, null);
        qrcode.setImageBitmap(bitmapQRCode);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyQRCodeActivity.class));
    }
}
