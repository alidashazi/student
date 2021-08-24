package com;

import com.hao.entity.User;
import com.hao.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * 测试获取一个对象的内容
 * RunWith 表示启动Junit测试启动器 使用SpringJUnit4ClassRunner
 * ContextConfiguration加载上下文配置文件将spring的配置文件加载到ioc容器中
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/baizhi/bill/spring/applicationContext.xml")
public class TestUser {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Test
    public void test() {
        try {
            SqlSessionFactory object = sqlSessionFactoryBean.getObject();
            SqlSession sqlSession = object.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.findUserUsername("admin");
            System.out.println(user.getPassword());
            System.out.println(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //add
    @Test
    public void test1() {
        try {
            SqlSessionFactory object = sqlSessionFactoryBean.getObject();
            SqlSession sqlSession = object.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user1 = new User();
            user1.setUsername("李四");
            user1.setPassword("123");
            mapper.add(user1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //findall
    @Test
    public void test2() throws Exception {
        SqlSessionFactory sf = sqlSessionFactoryBean.getObject();
        UserMapper um = sf.openSession().getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "%" + "%");
        map.put("offset", 1);
        map.put("pageSize", 2);

        List<User> all = um.findAll(map);
        for (User user : all) {
            System.out.println(user.getUsername());
        }
    }

    //删
    @Test
    public void test3() throws Exception {
        SqlSessionFactory sf = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sf.openSession();

        UserMapper um = sqlSession.getMapper(UserMapper.class);
        Integer[] ids = {3, 4};

        System.out.println(ids);

        int i = um.delete(ids);
        System.out.println(i);
        sqlSession.commit();
    }

    //删
    @Test
    public void test4() throws Exception {
        SqlSessionFactory sf = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sf.openSession();

        UserMapper um = sqlSession.getMapper(UserMapper.class);
        Integer[] ids = {3, 4};

        System.out.println(ids);

        int i = um.delete(ids);
        System.out.println(i);
        sqlSession.commit();
    }


}
