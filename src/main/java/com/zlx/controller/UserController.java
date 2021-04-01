package com.zlx.controller;

import com.zlx.entry.User;
import com.zlx.service.UserService;
import com.zlx.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController  //本身包含@ResponseBody
//@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "registry", method = RequestMethod.POST)
//    @ResponseBody //给前端的返回值
    public boolean registry(User user){
        if (null!=user.getUsername()&&null!=user.getPassword()){
            return userService.insertUser(user);
        }
        return false;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password){
        User user= userService.selectByUsername(username, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        if (null==user||null==user.getUsername()){
            return "用户不存在或用户名、密码错误";
        }
        String sign= JWTUtil.sign(user,60*1000L*30L);
        return "hello"+user.getUsername()+"token"+sign;
    }
}
