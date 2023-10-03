package com.store.android.ui.base.activity;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.store.android.MVVMApplication;
import com.store.android.data.Repository;
import com.store.android.data.model.other.ToastMessage;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;

public class BaseViewModel extends ViewModel{
    protected final ObservableBoolean mIsLoading = new ObservableBoolean();
    protected final MutableLiveData<String> progressBarMsg = new MutableLiveData<>();
    protected final MutableLiveData<ToastMessage> mErrorMessage = new MutableLiveData<>();
    protected final Repository repository;
    @Getter
    protected final MVVMApplication application;
    public CompositeDisposable compositeDisposable;
    @Setter
    protected String token;
    @Setter
    protected String deviceId;

    public BaseViewModel(Repository repository, MVVMApplication application) {
        this.repository = repository;
        this.application = application;
        this.compositeDisposable = new CompositeDisposable();
    }

//    public C getCallback() {
//        return mCallback.get();
//    }
//
//    public void setCallback(C callback) {
//        this.mCallback = new WeakReference<>(callback);
//    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public void showLoading() {
        mIsLoading.set(true);
    }

    public void hideLoading() {
        mIsLoading.set(false);
    }

    public void showSuccessMessage(String message) {
        mErrorMessage.setValue(new ToastMessage(ToastMessage.TYPE_SUCCESS, message));
    }

    public void showNormalMessage(String message) {
        mErrorMessage.setValue(new ToastMessage(ToastMessage.TYPE_NORMAL, message));
    }

    public void showWarningMessage(String message) {
        mErrorMessage.setValue(new ToastMessage(ToastMessage.TYPE_WARNING, message));
    }

    public void showErrorMessage(String message) {
        mErrorMessage.setValue(new ToastMessage(ToastMessage.TYPE_ERROR, message));
    }

    public void changeProgressBarMsg(String message) {
        progressBarMsg.setValue(message);
    }
}
