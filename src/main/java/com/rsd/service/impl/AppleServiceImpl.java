package com.rsd.service.impl;

import com.rsd.bean.Apple;
import com.rsd.mapper.IAppleMapper;
import com.rsd.service.IAppleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AppleServiceImpl implements IAppleService {

    @Override
    public List<Apple> queryListByIdOrPrice(Integer id, Integer price) {
        InputStream in = null;
        SqlSession session = null;
        List<Apple> list = new ArrayList<>();
        try {
            in = Resources.getResourceAsStream("mybatis.xml"); //加载主配置文件
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            session = factory.openSession();//创建和数据库交互的对象
            IAppleMapper appleMapper = session.getMapper(IAppleMapper.class);
            list = appleMapper.queryListByIdOrPrice(id, price);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
