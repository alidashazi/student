package com.hao.service;

import com.hao.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    User findUserUsername(String username);

    int add(User user);

    List<User> getList(Map<String, Object> map);

    int getTotal(Map<String, Object> map);

    int edit(User user);

    int delete(Integer[] ids);
}
