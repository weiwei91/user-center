package com.wei.usercenter;


import com.wei.usercenter.dao.user.UserMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class TestController {
    /**
     * // 作业1：课后研究一下@Resource和@Autowired的区别
     * // 面试题
     */
    @Resource
    private  UserMapper userMapper;

    public static String change(String p){
        char [] temps = p.toCharArray();
        List<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = temps.length-1; i >=0 ; i--) {
            if(!exit(temps[i],list)){
                list.add(temps[i]);
                sb.append(temps[i]);
            }
        }
        return sb.toString();
    }
    //判断一个字符是否在数组中
    public static boolean exit(char a,List<Character> list){
        if (list.size() == 0 || list ==null){
            return false;
        }
        for (Character c:list) {
            if (a==c){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {




    }




}
