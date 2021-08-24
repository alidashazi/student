package com.hao.mapper;

import com.hao.entity.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeMapper {
    //添加一个年级信息
    int add(Grade grade);

    //模糊查询查询所有用户信息
    List<Grade> findAll(Map<String, Object> map);

    //查询用户信息的总条数
    int getTotal(Map<String, Object> map);

    //修改一条用户数据
    int edit(Grade grade);

    //删除数据
    int delete(Integer[] ids);

    //查询所有年级
    List<Grade> findQueryAll();
}
