package com.blibli.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "用账号不能为空")
    private String userAccount;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "密码1不能为空")
    private String password1;

}
