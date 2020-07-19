package com.wei.usercenter;

import com.wei.usercenter.dao.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    /**
     * // 作业1：课后研究一下@Resource和@Autowired的区别
     * // 面试题
     */
    @Autowired
    private  UserMapper userMapper;


}
