package com.pfe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String status;
    private String password;
    private Boolean enabled;
    private Boolean accountLocked;
}
