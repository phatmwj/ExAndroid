package com.store.android.ui.login;

import android.content.Intent;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.store.android.MVVMApplication;
import com.store.android.R;
import com.store.android.data.Repository;
import com.store.android.data.model.api.request.LoginRequest;
import com.store.android.data.model.api.request.LoginRequest2;
import com.store.android.ui.base.activity.BaseViewModel;
import com.store.android.ui.home.HomeActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {
    private static final String TAG = "LoginViewModel";
    public ObservableField<String> username = new ObservableField<>("admin");
    public ObservableField<String> password = new ObservableField<>("admin123654");

    LoginCallback loginCallback;
    public LoginViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void login() {
        // check
        if (username.get() == null || username.get().isEmpty() || password.get() == null || password.get().isEmpty()) {
            showErrorMessage(application.getString(R.string.empty_user_pass));
            return;
        }
        LoginRequest2 loginRequest2 = new LoginRequest2();
        loginRequest2.setPassword(password.get());
        loginRequest2.setUsername(username.get());
        showLoading();
        compositeDisposable.add(repository.getApiService().login(loginRequest2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data.isResult()){
                        repository.getSharedPreferences().setToken(data.getData().getToken());
                        hideLoading();
                        Intent intent = new Intent(application.getCurrentActivity(), HomeActivity.class);
                        application.getCurrentActivity().startActivity(intent);
                        application.getCurrentActivity().finish();
                        showSuccessMessage(data.getMessage());
                    }else {

                        hideLoading();
                        showErrorMessage(data.getMessage());
                    }

                }, err -> {
                    hideLoading();
                    Log.d(TAG, "login: "+err.getMessage());
                    showErrorMessage("Error");
                }));
    }
}
