package com.blibli.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.StrUtil;
import com.blibli.constant.RedisConstants;
import com.blibli.dto.UserInfoDto;
import com.blibli.dto.UserRegisterDto;
import com.blibli.result.Result;
import com.blibli.service.UserInfoService;
import com.blibli.utils.RedisService;
import com.blibli.vo.UserCodeVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    RedisService redisService;

    @Override
    public String login(UserInfoDto userInfoDto) {
        return null;
    }

    @Override
    public String register(UserRegisterDto userRegisterDto) {
        return null;
    }


    @Override
    public void getCode(HttpSession session, HttpServletResponse response) {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40);
        // 设置返回数据类型
        response.setContentType("image/jpeg");
        // 禁止使用缓存
        response.setHeader("Pragma", "No-cache");
        try {
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 将 生成的验证码 和 验证码生成时间 存储到session中
            session.setAttribute("captcha", lineCaptcha.getCode());
            session.setAttribute("date", new Date());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean checkCode(String captcha, HttpSession session) {
        if(!StringUtils.hasLength(captcha)) {
            return false;
        }
        // 获取存储的验证码和生成时间
        String code = (String) session.getAttribute("captcha");
        Date createTime = (Date) session.getAttribute("date");
        // 判断验证码是否正确(验证码一般忽略大小写)
        if(captcha.equalsIgnoreCase(code)) {
            // 判断验证码是否过时
            if(createTime == null || System.currentTimeMillis() - createTime.getTime() < 60 * 1000) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String logout() {
        return null;
    }

    @Override
    public String updatePwd(UserRegisterDto userRegisterDto) {
        return null;
    }
}
