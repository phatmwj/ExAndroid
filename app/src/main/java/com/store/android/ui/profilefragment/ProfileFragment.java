package com.store.android.ui.profilefragment;

import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.store.android.BR;
import com.store.android.R;
import com.store.android.databinding.FragmentProfileBinding;
import com.store.android.di.component.FragmentComponent;
import com.store.android.ui.base.fragment.BaseFragment;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileFragmentViewModel> {

    private static final String TAG = "ProfileFragment";
    @Inject
    PermissionAdapter permissionAdapter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    FragmentProfileBinding fragmentProfileBinding;

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void performDataBinding() {
        fragmentProfileBinding = getViewDataBinding();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentProfileBinding.rcPermission.setLayoutManager(linearLayoutManager);
        fragmentProfileBinding.rcPermission.setItemAnimator(new DefaultItemAnimator());
        fragmentProfileBinding.rcPermission.setAdapter(permissionAdapter);
    }
    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }


//        Log.d(TAG, "performDataBinding: ");
//        Log.d(TAG, "performDataBinding: "+getViewModel().getPermissions());
//    permissionAdapter = new PermissionAdapter(getViewModel().getPermissions());
}
