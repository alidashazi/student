package com.hao.service;


import com.hao.entity.Clazz;
import com.hao.mapper.ClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

//changed
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;

    @Override
    public List<Clazz> findQueryAll() {
        return clazzMapper.findQueryAll();
    }

    @Override
    public int add(Clazz clazz) {
        return clazzMapper.add(clazz);
    }

    @Override
    public List<Clazz> getList(HashMap<String, Object> map) {
        return clazzMapper.findAll(map);
    }

    @Override
    public int getTotal(HashMap<String, Object> map) {
        return clazzMapper.getTotal(map);
    }
}
