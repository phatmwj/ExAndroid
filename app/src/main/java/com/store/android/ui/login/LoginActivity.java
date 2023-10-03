package com.store.android.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.store.android.R;
import com.store.android.databinding.ActivityLoginBinding;
import com.store.android.di.component.ActivityComponent;
import com.store.android.ui.base.activity.BaseActivity;
import com.store.android.ui.home.HomeActivity;
import com.store.android.ui.main.MainActivity;

import eu.davidea.flexibleadapter.databinding.BR;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginCallback{
    private static final String TAG = "LoginActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void doError(Throwable error) {

    }

    @Override
    public void doSuccess() {
        Log.d(TAG, "doSuccess: ");
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void doFail() {

    }
}