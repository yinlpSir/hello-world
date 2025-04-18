package com.hngy.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterDto {
    @NotBlank(message = "name.not.null")
    private String username;

    @NotBlank(message = "password.not.null")
    @Length(min = 6,message = "password.length")
    private String password;

    @NotBlank(message = "number cannot be null")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
    private String number;
}
