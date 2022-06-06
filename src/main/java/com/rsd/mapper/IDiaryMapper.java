package com.rsd.mapper;

import com.rsd.bean.Diary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDiaryMapper {
    List<Diary> queryList();

    List<Diary> queryListByParam(Diary diary);

//    List<Diary> queryListByIds(String ids); //1,4,6
    List<Diary> queryListByIds(@Param("iArray") Integer[] iArray);

    List<Diary> queryListByTitle(String title);

    Diary getById(Integer id);

    void insert(Diary diary);

    void insertList(@Param("diaryList") List<Diary> diaryList);

    void updateForNull(Diary diary);

    void update(Diary diary);

    void deleteById(Integer id);
}
