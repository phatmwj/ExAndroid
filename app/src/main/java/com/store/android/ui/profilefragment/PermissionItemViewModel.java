package com.store.android.ui.profilefragment;

import androidx.databinding.ObservableField;

import com.store.android.data.model.api.response.Permission;

public class PermissionItemViewModel {

    private Permission permission;

    private PermissionItemListener permissionItemLitener;

    public ObservableField<String> name= new ObservableField<>("aaa");
    public PermissionItemViewModel(Permission permission, PermissionItemListener permissionItemListener) {
        this.permission = permission;
        this.permissionItemLitener = permissionItemListener;
        name.set(permission.getName());
    }

    public void OnItemClick(){
        permissionItemLitener.OnItemClick();
    }
    public interface PermissionItemListener{
        void OnItemClick();
    }
}
