package com.store.android.ui.profile;

import androidx.databinding.ObservableField;

import com.store.android.MVVMApplication;
import com.store.android.data.Repository;
import com.store.android.ui.base.activity.BaseViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel {

    private static final String TAG = "Profile";
    public ObservableField<String> name = new ObservableField<>("fullname");
    public ObservableField<String> phone = new ObservableField<>("phone");
    public ObservableField<String> image = new ObservableField<>("avartar");

    public ProfileViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        profile();
    }

    public void profile() {
        showLoading();
        compositeDisposable.add(repository.getApiService().profile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if(data.isResult()){
                        name.set(data.getData().getFullName());
                        phone.set(data.getData().getPhone());
                        image.set(data.getData().getAvatar());
                        hideLoading();
                        showSuccessMessage(data.getMessage());
                    }else {
                        hideLoading();
                        showErrorMessage(data.getMessage());
                    }
                }, err -> {
                    hideLoading();
                    showErrorMessage("Error");
                }));
    }

}
