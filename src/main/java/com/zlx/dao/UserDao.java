package com.zlx.dao;

import com.zlx.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Insert("insert into User(id,username,password) values(#{id},#{username},#{password})")
    int insertUser(User user);

    @Update("upadte User set password=#{password} where username=#{username}")
    int updateUser(String password,String username);

    @Select("select*from User where username=#{username} and password=#{password}")
    User selectByUsernameAndPassword(String username,String password);

    @Select("select*from User where username=#{username}")
    User selectByUsername(String username);
}
