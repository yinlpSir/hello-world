package com.hngy.lms.dto;

import com.hngy.lms.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginDto {
    @NotBlank(message = "name.not.null")
    private String username;

    @NotBlank(message = "password.not.null")
    @Length(min = 6,message = "password.length")
    private String password;

//    @Enumerated(EnumType.STRING)
//    @NotNull(message = "role.not.null")
//    private Role role;
}
