package com.euler.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;




    @Test
    void contextLoads() {
        // 测试删除用户
//        System.out.println(userService.deleteUserByUserName("test").getData());
        // 测试修改用户信息
    }
}