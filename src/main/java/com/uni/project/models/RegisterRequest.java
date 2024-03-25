package com.uni.project.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "pin is mandatory")
    @Length(min=7 ,max = 7 ,message = "invalid pin")
    private String pin;
    @NotBlank(message = "password is mandatory")

    private String password;
}
