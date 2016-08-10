package com.bearkiddiary.enterprise.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.utils.ChartUtil;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YarenChoi on 2016/8/10.
 * 学生信息统计界面
 */
public class StudentInfoDetailFragment extends BaseFragment {
    private Context mContext;
    private LinearLayout ll_height;
    private LinearLayout ll_weight;

    private ChartUtil heightChart;
    private ChartUtil weightChart;
    private GraphicalView heightGraphcalView;
    private GraphicalView weightGraphcalView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info_detail, container, false);
        mContext = inflater.getContext();
        initView(view);

        //setHeightChartView();
        return view;
    }

    private void initView(View view) {
        ll_height = (LinearLayout) view.findViewById(R.id.ll_student_info_height);

        heightChart = new ChartUtil(mContext);
    }

    private void setHeightChartView(){
//        heightChart.setMultipleSeriesDataset(new String[]{"身高"});
        //Color.argb(255, 255, 64, 129)
        heightChart.setMultipleSeriesRenderer(180, 12, "厘米（cm）", "月", "身高", Color.BLACK, Color.BLACK, Color.BLACK, new int[]{});
        heightGraphcalView = heightChart.getmGraphcalView();

        for(int i = 1; i < 13; i++){
            heightChart.addXTextLabel(i, i + "");
        }
        ll_height.addView(heightGraphcalView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        List<Integer> datalist = new ArrayList<>();

        datalist.add(130);
        datalist.add(131);
        datalist.add(134);
        datalist.add(136);
        datalist.add(136);
        datalist.add(137);
        datalist.add(137);
        datalist.add(137);
        datalist.add(138);
        datalist.add(138);
        datalist.add(138);
        datalist.add(138);
        datalist.add(140);

        for (int j = 1; j < 13; j++){
            heightChart.updataChart(j,datalist.get(j-1));
            Log.d("chart", "updataChart ");
        }

        Log.d("chart","执行了");
    }
}
