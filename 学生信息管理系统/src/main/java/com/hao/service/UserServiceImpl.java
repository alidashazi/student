package com.hao.service;

import com.hao.entity.User;
import com.hao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getList(Map<String, Object> map) {
        return userMapper.findAll(map);
    }

    @Override
    public int getTotal(Map<String, Object> map) {
        return userMapper.getTotal(map);
    }

    @Override
    public int edit(User user) {
        return userMapper.edit(user);
    }

    @Override
    public int delete(Integer[] ids) {
        return userMapper.delete(ids);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public User findUserUsername(String username) {
        User user = userMapper.findUserUsername(username);

        return user;
    }
}
