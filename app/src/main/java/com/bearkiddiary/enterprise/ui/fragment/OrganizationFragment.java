package com.bearkiddiary.enterprise.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.ui.activity.CourseActivity;
import com.bearkiddiary.enterprise.ui.activity.StaffActivity;
import com.bearkiddiary.enterprise.ui.view.IconButton;
import com.gc.materialdesign.views.Card;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by zy on 2016/7/22.
 */
public class OrganizationFragment extends BaseFragment {

    private AppCompatSpinner spinner_org;
    private IconButton ib_location;
    private ImageView iv_up, iv_down;
    private RecyclerView rv_course;
    private ViewStub vs_manager;
    private LinearLayout ll_manager, ll_org;
    private OrganizationAdapter mAdapter;
    private int curHeight;//控制上下拉的高度
    private boolean middle = true;//控制上下拉状态

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization, container, false);
        initView(view);
        return view;
    }

    private final void initView(View v) {
        vs_manager = (ViewStub) v.findViewById(R.id.vs_organization_manager);
        rv_course = (RecyclerView) v.findViewById(R.id.rv_organization_course);
        ib_location = (IconButton) v.findViewById(R.id.ib_location);
        iv_up = (ImageView) v.findViewById(R.id.iv_organization_up);
        iv_down = (ImageView) v.findViewById(R.id.iv_organization_down);
        spinner_org = (AppCompatSpinner) v.findViewById(R.id.spinner_organization);
        ll_manager = (LinearLayout) v.findViewById(R.id.ll_organization_manager);
        ll_org = (LinearLayout) v.findViewById(R.id.ll_organization);

        ib_location.setOnClickListener(view -> StaffActivity.startActivity(getContext()));

        //如果是机构管理员，则显示管理员的选项界面
        vs_manager.inflate();

        //课程列表
        rv_course.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_course.setItemAnimator(new DefaultItemAnimator());
        rv_course.setAdapter(mAdapter = new OrganizationAdapter());

        //上下拉箭头
        final int dp104 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 104, getResources().getDisplayMetrics());//滑动距离
        curHeight = dp104;
        View.OnClickListener onUpDownListener = view -> {
            int to = 0;
            if (middle) {
                middle = false;
                if (view.getId() == R.id.iv_organization_up)
                    to = 0;
                else if (view.getId() == R.id.iv_organization_down)
                    to = rv_course.getHeight() + ll_manager.getHeight();
            } else {
                middle = true;
                to = dp104;
            }
            ValueAnimator animator = ValueAnimator.ofInt(curHeight, to);
            animator.setDuration(300);
            animator.addUpdateListener(animation -> {
                curHeight = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = ll_manager.getLayoutParams();
                params.height = curHeight;
                ll_manager.setLayoutParams(params);
            });
            animator.start();
        };
        iv_up.setOnClickListener(onUpDownListener);
        iv_down.setOnClickListener(onUpDownListener);
    }

    class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

        @Override
        public OrganizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_organization_course, parent, false);
            return new OrganizationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OrganizationViewHolder holder, int position) {
            holder.card_course.setOnClickListener(view -> CourseActivity.startActivity(getContext()));
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class OrganizationViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_name, tv_teacher, tv_time;
            private Card card_course;

            public OrganizationViewHolder(View v) {
                super(v);
                card_course = (Card) v.findViewById(R.id.card_organization_course);
                tv_name = (TextView) v.findViewById(R.id.tv_organization_course_name);
                tv_teacher = (TextView) v.findViewById(R.id.tv_organization_course_teacher);
                tv_time = (TextView) v.findViewById(R.id.tv_organization_course_time);
            }
        }
    }
}
