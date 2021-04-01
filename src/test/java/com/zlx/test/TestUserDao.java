package com.zlx.test;

import com.zlx.OrderApplication;
import com.zlx.dao.UserDao;
import com.zlx.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void testDao() {
        //表名和类名一样
        User user = new User();
        user.setUsername("2");
        user.setPassword("2");
        userDao.insertUser(user);
    }
}
