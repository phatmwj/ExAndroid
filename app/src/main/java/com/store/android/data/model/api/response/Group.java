package com.store.android.data.model.api.response;

import java.util.List;

import lombok.Data;

@Data
public class Group {
    private String name;
    private List<Permission> permissions;
}
