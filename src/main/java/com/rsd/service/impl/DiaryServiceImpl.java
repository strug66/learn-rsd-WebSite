package com.rsd.service.impl;

import com.rsd.bean.Diary;
import com.rsd.mapper.IDiaryMapper;
import com.rsd.service.IDiaryService;
import com.rsd.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class DiaryServiceImpl implements IDiaryService {
    @Override
    public List<Diary> queryList() {
        List<Diary> list = new ArrayList<>();

        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        list = iDiaryMapper.queryList();

        session.close();
        return list;
    }

    @Override
    public List<Diary> queryListByParam(Diary diary) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = iDiaryMapper.queryListByParam(diary);

        session.close();
        return list;
    }

    @Override
    public List<Diary> queryListByIds(Integer[] iArray) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = iDiaryMapper.queryListByIds(iArray);

        session.close();
        return list;
    }

    @Override
    public List<Diary> queryListByTitle(String title) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = iDiaryMapper.queryListByTitle(title);

        session.close();
        return list;
    }

    @Override
    public Diary getById(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        Diary diary = iDiaryMapper.getById(id);

        session.close();
        return diary;
    }

    @Override
    public void insert(Diary diary) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        iDiaryMapper.insert(diary);

        session.commit();
        session.close();
    }

    @Override
    public void insertList(List<Diary> diaryList) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        iDiaryMapper.insertList(diaryList);

        session.commit();
        session.close();
    }

    @Override
    public void updateForNull(Diary diary) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        iDiaryMapper.updateForNull(diary);

        session.commit();
        session.close();
    }

    @Override
    public void update(Diary diary) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        iDiaryMapper.update(diary);

        session.commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        IDiaryMapper iDiaryMapper = session.getMapper(IDiaryMapper.class);
        iDiaryMapper.deleteById(id);

        session.commit();
        session.close();
    }

//    private SqlSession getSqlSession() {
//        InputStream in = null;
//        SqlSession session = null;
//        try {
//            in = Resources.getResourceAsStream("mybatis.xml");
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            SqlSessionFactory factory = builder.build(in);
//
//            session = factory.openSession();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (in != null) { //直接关闭流对象in
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return session;
//    }

}
