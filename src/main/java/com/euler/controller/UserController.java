package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestParam String username, @RequestParam String password, HttpServletRequest httpServletRequest) {
        return userService.login(username, password, httpServletRequest);
    }
}
