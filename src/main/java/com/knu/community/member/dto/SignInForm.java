package com.knu.community.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInForm {

    @Email
    @NotBlank
    private String email;

    private String password;

    public SignInForm(){}

    public SignInForm(
        @Email @NotBlank String email, String password) {
        this.email = email;
        this.password = password;
    }
}
