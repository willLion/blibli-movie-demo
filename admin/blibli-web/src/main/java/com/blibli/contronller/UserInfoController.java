package com.blibli.contronller;

import com.blibli.dto.UserInfoDto;
import com.blibli.dto.UserRegisterDto;
import com.blibli.entity.UserInfo;
import com.blibli.result.Result;
import com.blibli.service.UserInfoService;
import com.blibli.vo.UserCodeVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result<String> test() {
        return Result.success("test success");
    }


    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestBody @Validated UserInfoDto userInfoDto) {
        return Result.success(userInfoService.login(userInfoDto));
    }


    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<String> register(@RequestBody @Validated UserRegisterDto userInfoDto) {
        return Result.success(userInfoService.register(userInfoDto));
    }


    /**
     * 获取验证码
     *
     * @return
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public Result<String> getCode(HttpSession session, HttpServletResponse response) {
        return Result.success("ok");
    }

    /**
     * 验证码校验
     *
     * @return
     */
    @RequestMapping(value = "/checkCode", method = RequestMethod.GET)
    public Result<String> checkCode(String captcha, HttpSession session) {
        log.info("验证码:", captcha);
        return Result.success(userInfoService.checkCode(captcha, session) ? "ok" : "fail");
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result<String> updatePwd(@RequestBody @Validated UserRegisterDto userInfoDto) {
        return Result.success(userInfoService.updatePwd(userInfoDto));
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result<String> logout() {
        return Result.success(userInfoService.logout());
    }

}
