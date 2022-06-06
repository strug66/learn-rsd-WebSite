package com.rsd.service.impl;

import com.rsd.bean.Education;
import com.rsd.mapper.IEducationMapper;
import com.rsd.service.IEducationService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EducationServiceImpl2 implements IEducationService {
    @Override
    public List<Education> queryList() {
        SqlSession session = getSqlSession();
        IEducationMapper educationMapper = session.getMapper(IEducationMapper.class);
        List<Education> list = educationMapper.queryList();
        System.out.println("=======" + list);

//        System.out.println("---------1、通过clearCache方法清除session的缓存内容。-------");
//        session.clearCache();


//        System.out.println("---------2、当执行insert/update/delete操作时，session的缓存被清除。-------");
//        Education education = new Education();
//        education.setName("初中");
//        educationMapper.insert(education);

//        System.out.println("---------3、在select的SQL标签上增加flushCache='true'，查询结果不进入缓存。-------");

        List<Education> list2 = educationMapper.queryList();
        System.out.println("========" + list2);

        session.close();
        return list;
    }

    @Override
    public void insert(Education education) {
        SqlSession session = getSqlSession();
        IEducationMapper educationMapper = session.getMapper(IEducationMapper.class);
        educationMapper.insert(education);

        session.commit(); //提交
        session.close();
    }

    private SqlSession getSqlSession() {
        InputStream in = null;
        SqlSession session = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);

            session = factory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) { //直接关闭流对象in
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return session;
    }

}
