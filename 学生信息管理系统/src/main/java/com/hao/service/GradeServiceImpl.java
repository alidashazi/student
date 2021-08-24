package com.hao.service;

import com.hao.entity.Grade;
import com.hao.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public int add(Grade grade) {
        return gradeMapper.add(grade);
    }

    @Override
    public List<Grade> getList(Map<String, Object> map) {
        return gradeMapper.findAll(map);
    }

    @Override
    public int getTotal(Map<String, Object> map) {
        return gradeMapper.getTotal(map);
    }

    @Override
    public int edit(Grade grade) {
        return gradeMapper.edit(grade);
    }

    @Override
    public int delete(Integer[] ids) {
        return gradeMapper.delete(ids);
    }

    @Override
    public List<Grade> findQueryAll() {
        return gradeMapper.findQueryAll();
    }
}
