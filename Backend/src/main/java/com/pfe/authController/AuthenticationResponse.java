package com.pfe.authController;

import com.pfe.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token ;
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role roles ;




}
