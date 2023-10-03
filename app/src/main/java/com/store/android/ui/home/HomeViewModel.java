package com.store.android.ui.home;

import com.store.android.MVVMApplication;
import com.store.android.data.Repository;
import com.store.android.ui.base.activity.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
