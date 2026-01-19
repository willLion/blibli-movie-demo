package com.blibli.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    @NotBlank(message = "用账号不能为空")
    private String userAccount;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证结果key不能为空")
    private String key;

    @NotBlank(message = "验证结果不能为空")
    private String code;
}
