package com.zlx.service;

import com.zlx.dao.UserDao;
import com.zlx.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean insertUser(User user) {
        User user1 = userDao.selectByUsername(user.getUsername());
        if (null != user1 && user1.getUsername() != null && user1.getUsername().equals(user.getUsername())) {
            return true;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        return userDao.insertUser(user) != 0;
    }
    public User selectByUsername(String username,String password){
        User user=null;
        if (null!=username){
            user = userDao.selectByUsernameAndPassword(username, password);
        }
        return user;
    }
}
