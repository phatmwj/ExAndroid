package com.store.android.di.component;


import com.store.android.di.module.FragmentModule;
import com.store.android.di.scope.FragmentScope;
import com.store.android.ui.profilefragment.ProfileFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(ProfileFragment profileFragment);
}
