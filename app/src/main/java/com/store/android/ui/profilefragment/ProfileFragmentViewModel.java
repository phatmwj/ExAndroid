package com.store.android.ui.profilefragment;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.store.android.MVVMApplication;
import com.store.android.data.Repository;
import com.store.android.data.model.api.response.Permission;
import com.store.android.ui.base.fragment.BaseFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileFragmentViewModel extends BaseFragmentViewModel {

    private static final String TAG = "ProfileFragmentViewModel";
    private MutableLiveData<List<Permission>> permissionListLiveData;
    private List<Permission> permissions = new ArrayList<>();
//    public ObservableField<List<Permission>> permissions= new ObservableField<>();

    public ObservableField<String> name = new ObservableField<>("fullname");
    public ObservableField<String> phone = new ObservableField<>("phone");
    public ObservableField<String> image = new ObservableField<>("avartar");
    public ProfileFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
//        permissionListLiveData = new MutableLiveData<>();
//        profile();
        Log.d(TAG, "ProfileFragmentViewModel: "+permissionListLiveData);

    }

    public void profile() {
        showLoading();
        compositeDisposable.add(repository.getApiService().profile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if(data.isResult()){
                        Log.d(TAG, "profile: 1");
                        name.set(data.getData().getFullName());
                        phone.set(data.getData().getPhone());
                        image.set(data.getData().getAvatar());
                        Log.d(TAG, "profile: "+data.getData().getFullName());
                        if(data!=null)
                            if(data.getData()!=null)
                                if(data.getData().getGroup()!=null)
                                    if(data.getData().getGroup().getPermissions()!=null)
                                        permissionListLiveData.setValue(data.getData().getGroup().getPermissions());
                        Log.d(TAG, "profile: "+ permissionListLiveData.getValue());
                        permissions=data.getData().getGroup().getPermissions();
                        Log.d(TAG, "profile: "+permissions);
                        hideLoading();
                        showSuccessMessage(data.getMessage());
                    }else {
                        hideLoading();
                        showErrorMessage(data.getMessage());
                    }
                }, err -> {
                    Log.d(TAG, "profile: "+err.getMessage());
                    hideLoading();
                    showErrorMessage("Error");
                }));
    }

    public MutableLiveData<List<Permission>> getPermissionListLiveData() {
        if(permissionListLiveData ==null){
            permissionListLiveData = new MutableLiveData<>();
            profile();
//            permissionListLiveData.setValue(permissions);
        }
        Log.d(TAG, "getPermissionListLiveData: "+permissionListLiveData.getValue());
        return permissionListLiveData;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
//    public void setPermissionListLiveData(MutableLiveData<List<Permission>> permissionListLiveData) {
//        this.permissionListLiveData = permissionListLiveData;
//    }
}
