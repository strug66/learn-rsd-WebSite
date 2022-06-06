package com.rsd.service.impl;

import com.rsd.bean.NewsInfo;
import com.rsd.mapper.INewsInfoMapper;
import com.rsd.service.INewsInfoService;
import com.rsd.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsInfoServiceImpl implements INewsInfoService {
    @Override
    public List<NewsInfo> queryList() {
        SqlSession session = MybatisUtil.getSession();
        INewsInfoMapper newsInfoMapper = session.getMapper(INewsInfoMapper.class);
        List<NewsInfo> list = newsInfoMapper.queryList();

        session.close();
        return list;
    }

    @Override
    public NewsInfo getById(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        INewsInfoMapper newsInfoMapper = session.getMapper(INewsInfoMapper.class);
        NewsInfo newsInfo = newsInfoMapper.getById(id);

        session.close();
        return newsInfo;
    }

    @Override
    public void insert(NewsInfo newsInfo) {
        SqlSession session = MybatisUtil.getSession();
        INewsInfoMapper newsInfoMapper = session.getMapper(INewsInfoMapper.class);
        newsInfoMapper.insert(newsInfo);

        session.commit();
        session.close();
    }

    @Override
    public void update(NewsInfo newsInfo) {
        SqlSession session = MybatisUtil.getSession();
        INewsInfoMapper newsInfoMapper = session.getMapper(INewsInfoMapper.class);
        newsInfoMapper.update(newsInfo);

        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = MybatisUtil.getSession();
        INewsInfoMapper newsInfoMapper = session.getMapper(INewsInfoMapper.class);
        newsInfoMapper.delete(id);

        session.commit();
        session.close();
    }
}
