package com.store.android.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.store.android.R;
import com.store.android.databinding.ActivityHomeBinding;
import com.store.android.di.component.ActivityComponent;
import com.store.android.ui.base.activity.BaseActivity;
import com.store.android.ui.profilefragment.ProfileFragment;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements NavigationBarView.OnItemSelectedListener{
    private static final String TAG = "HomeActivity";

    BottomNavigationView bottomNavigationView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.search);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.search:
//                SearchFragment searchFragment = new SearchFragment();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frameLayout, searchFragment)
//                        .setReorderingAllowed(true)
//                        .commit();
//                return true;
//            case R.id.shopping:
//                ShoppingFragment shoppingFragment = new ShoppingFragment();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frameLayout, shoppingFragment)
//                        .setReorderingAllowed(true)
//                        .commit();
//                return true;
            case R.id.account:
                ProfileFragment profileFragment = new ProfileFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, profileFragment)
                        .setReorderingAllowed(true)
                        .commit();
                Log.d(TAG, "onNavigationItemSelected: profile");
                return true;
        }
        return false;
    }
    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }
}
