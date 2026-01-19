package com.blibli.service;

import com.blibli.dto.UserInfoDto;
import com.blibli.dto.UserRegisterDto;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface UserInfoService {
    String login(UserInfoDto userInfoDto);
    String register(UserRegisterDto userRegisterDto);
    void getCode(HttpSession session, HttpServletResponse response);
    Boolean checkCode(String captcha, HttpSession session);
    String logout();
    String updatePwd(UserRegisterDto userRegisterDto);
}
