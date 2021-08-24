package com.hao.mapper;

import com.hao.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    User findUserUsername(String username);

    int add(User user);

    //模糊查询查询所有用户信息
    List<User> findAll(Map<String, Object> map);

    //查询用户信息的总条数
    int getTotal(Map<String, Object> map);

    //修改一条用户数据
    int edit(User user);

    //删除数据
    int delete(Integer[] ids);
}
