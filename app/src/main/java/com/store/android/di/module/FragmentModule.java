package com.store.android.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.store.android.MVVMApplication;
import com.store.android.ViewModelProviderFactory;
import com.store.android.data.Repository;
import com.store.android.di.scope.FragmentScope;
import com.store.android.ui.base.fragment.BaseFragment;
import com.store.android.ui.login.LoginViewModel;
import com.store.android.ui.profilefragment.PermissionAdapter;
import com.store.android.ui.profilefragment.ProfileFragment;
import com.store.android.ui.profilefragment.ProfileFragmentViewModel;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Named("access_token")
    @Provides
    @FragmentScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    @FragmentScope
    PermissionAdapter providePermissionAdapter(){
        return new PermissionAdapter(new ArrayList<>());
    }

    @Provides
    @FragmentScope
    ProfileFragmentViewModel provideProfileFragmentViewModel(Repository repository, Context application){
        Supplier<ProfileFragmentViewModel> supplier = () -> new ProfileFragmentViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ProfileFragmentViewModel> factory = new ViewModelProviderFactory<>(ProfileFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileFragmentViewModel.class);
    }

}
