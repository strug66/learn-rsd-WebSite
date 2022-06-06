package com.rsd.day5;

import com.rsd.bean.Diary;
import com.rsd.mapper.IDiaryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        InputStream in = null;
        SqlSession session = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);

            session = factory.openSession();
            IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);

            List<Diary> list = iDiaryMapper.queryList();
            System.out.println(list);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(session!=null){
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
    }
}
