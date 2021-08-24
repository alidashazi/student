package com.hao.service;

import com.hao.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GradeService {

    int add(Grade grade);

    List<Grade> getList(Map<String, Object> map);

    int getTotal(Map<String, Object> map);

    int edit(Grade grade);

    int delete(Integer[] ids);

    List<Grade> findQueryAll();
}
