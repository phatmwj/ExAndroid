package com.store.android.data.model.api.response;

import lombok.Data;

@Data
public class ProfileResponse {

    private String fullName;
    private String email;
    private String avatar;
    private String phone;
    private String username;
}
