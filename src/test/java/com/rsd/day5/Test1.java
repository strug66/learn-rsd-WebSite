package com.rsd.day5;

import com.rsd.bean.Apple;
import com.rsd.mapper.IAppleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test1 {
    public static void main(String[] args) {
        InputStream in = null;
        SqlSession session = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml"); //加载主配置文件

//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            SqlSessionFactory factory = builder.build(in);

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in); //创建sqlSessionFactory，通过工厂对象可以创建多个session对象

            session = factory.openSession();//创建和数据库交互的对象

//            //方法1
//            List<SysFiles> list = session.selectList("com.rsd.DAO.SysFileDAO.queryList");//字符串：命名空间+“.”+id的值
//            System.out.println(list);
//
//            SysFiles o = session.selectOne("com.rsd.DAO.SysFileDAO.getById",13);
//            System.out.println(o);
//
//            List<Apple> list1 = session.selectList("apple.queryList");
//            System.out.println(list1);

//            //方法2
//            ISysFilesMapper mapper1 = session.getMapper(ISysFilesMapper.class);
//            List<SysFiles> sysFiles = mapper1.queryList();
//            System.out.println(sysFiles);
//
//            SysFiles sysFiles1 = mapper1.getById(15);
//            System.out.println(sysFiles1);
//
            IAppleMapper appleMapper = session.getMapper(IAppleMapper.class);
//            List<Apple> apples = appleMapper.queryList();
//            System.out.println(apples);
//
//            Apple apple = appleMapper.getById(1);
//            System.out.println(apple);
//
//            List<Apple> apples1 = appleMapper.queryListByIdOrPrice(1, 23);
//            System.out.println(apples1);

            Apple apple1 = new Apple();
            apple1.setId(3);
            apple1.setName("aaa");
            apple1.setPrice(20);

//            appleMapper.insert(apple1);
//            session.commit();//提交事务

//            appleMapper.update(apple1);
//            session.commit();

            appleMapper.delete(3);
            session.commit();

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

    }
}
