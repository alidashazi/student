package com;

import com.hao.entity.Grade;
import com.hao.mapper.GradeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/baizhi/bill/spring/applicationContext.xml")
public class TestGrade {
    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;

    @Test
    public void test1() {
        try {
            SqlSession sqlSession = sqlSessionFactory.getObject().openSession();

            GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);

            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("name", "%%");
            stringObjectHashMap.put("offset", 1);
            stringObjectHashMap.put("pageSize", 10);


            List<Grade> all = mapper.findAll(stringObjectHashMap);
            int total = mapper.getTotal(stringObjectHashMap);
            System.out.println(total);
            System.out.println(all.size());
            for (Grade grade : all) {
                System.out.println(grade.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test4() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.getObject().openSession();

        GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);

        List<Grade> all = mapper.findQueryAll();
        all.forEach(new Consumer<Grade>() {
            @Override
            public void accept(Grade grade) {
                System.out.println(grade);
            }
        });
    }
}
