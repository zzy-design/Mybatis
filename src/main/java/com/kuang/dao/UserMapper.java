package com.kuang.dao;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据id查询用户
    User getUserById( int id);

    //limit实现分页
    List<User> getUserByLimit(Map<String,Integer> map);

    //根据name模糊查询
    List<User> getUserByName(String name);

    //添加用户
    int insertUser(User user);

    //根据Map添加用户
    int insertUser2(Map<String,Object> map);

    //更新用户
    int updataUser(User user);

    //删除用户
    int deleteUser(int id);
}
