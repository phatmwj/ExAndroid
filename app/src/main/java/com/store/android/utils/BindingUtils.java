package com.store.android.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.store.android.R;
import com.store.android.data.model.api.response.Permission;
import com.store.android.ui.profilefragment.PermissionAdapter;

import java.util.List;

public final class BindingUtils {

    @BindingAdapter({"adapter"})
    public static void addPermissionItems(RecyclerView recyclerView, List<Permission> permissions) {
        PermissionAdapter adapter = (PermissionAdapter) recyclerView.getAdapter();
        if (adapter != null) {
//            adapter.clearItems();
            adapter.addItems(permissions);
        }
    }
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.drawable.logo)
                .into(view);
    }
}
