package com.euler.service;

import com.euler.bo.BaseResponse;
import com.euler.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户service接口
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param
     * @param
     * @return http返回体，其中的data为所有用户信息的列表
     */
    BaseResponse login(String username, String password, HttpServletRequest httpServletRequest);
}
