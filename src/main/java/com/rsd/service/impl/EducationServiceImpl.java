package com.rsd.service.impl;

import com.rsd.bean.Education;
import com.rsd.mapper.IEducationMapper;
import com.rsd.service.IEducationService;
import com.rsd.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EducationServiceImpl implements IEducationService {
    @Override
    public List<Education> queryList() {
        SqlSession session = MybatisUtil.getSession();
        IEducationMapper educationMapper = session.getMapper(IEducationMapper.class);
        List<Education> list = educationMapper.queryList();

        session.close();
        return list;
    }

    @Override
    public void insert(Education education) {
        SqlSession session = MybatisUtil.getSession();
        IEducationMapper educationMapper = session.getMapper(IEducationMapper.class);
        educationMapper.insert(education);

        session.commit(); //提交
        session.close();
    }
}
