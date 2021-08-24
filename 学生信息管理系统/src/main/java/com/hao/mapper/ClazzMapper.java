package com.hao.mapper;

import com.hao.entity.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClazzMapper {
    //添加一个年级信息
    int add(Clazz clazz);

    //模糊查询查询所有用户信息
    List<Clazz> findAll(Map<String, Object> map);

    //查询用户信息的总条数
    int getTotal(Map<String, Object> map);

    //修改一条用户数据
    int edit(Clazz clazz);

    //删除数据
    int delete(Integer[] ids);

    //查询所有年级
    List<Clazz> findQueryAll();
}
