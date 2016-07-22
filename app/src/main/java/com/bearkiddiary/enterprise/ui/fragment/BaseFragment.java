package com.bearkiddiary.enterprise.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.bearkiddiary.enterprise.ui.fragment.ifragment.IBaseFragment;

public class BaseFragment extends Fragment implements IBaseFragment {
    @Override
    public void showToast(String str) {
        if (getContext() != null)
            Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
