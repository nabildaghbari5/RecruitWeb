package com.pfe.authController;

import com.pfe.model.Role;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String telephone ;
    private String status;
    private String roles ;

}