package com.store.android.ui.profilefragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.store.android.data.model.api.response.Permission;
import com.store.android.databinding.ItemPermissionBinding;
import com.store.android.ui.base.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "PermissionAdapter";
    private List<Permission> permissions = new ArrayList<>();

    public PermissionAdapter(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPermissionBinding itemPermissionBinding = ItemPermissionBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PermissionViewHolder(itemPermissionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: "+permissions.size());
        return permissions== null ? 0 : permissions.size();
    }

    public void addItems(List<Permission> permissions) {
//        this.permissions.add(new Permission("name","aaa"));
//        this.permissions.add(new Permission("hai","a"));
        this.permissions =permissions;
        notifyDataSetChanged();
    }

    public void clearItems() {
        permissions.clear();
    }

    public class PermissionViewHolder extends BaseViewHolder implements PermissionItemViewModel.PermissionItemListener{

        private ItemPermissionBinding mBinding;

        private PermissionItemViewModel mPermissionItemViewModel;

        public PermissionViewHolder(ItemPermissionBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Permission permission = permissions.get(position);
            mPermissionItemViewModel = new PermissionItemViewModel(permission, this);
            mBinding.setIvm(mPermissionItemViewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void OnItemClick() {
            Log.d(TAG, "OnItemClick: ");
        }
    }
}
