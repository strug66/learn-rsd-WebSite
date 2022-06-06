package com.rsd.day5;

import com.rsd.bean.Diary;
import com.rsd.mapper.IDiaryMapper;
import com.rsd.service.IDiaryService;
import com.rsd.service.impl.DiaryServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        IDiaryService diaryService = new DiaryServiceImpl();
//        diaryService.queryListByIds(new Integer[]{1,6,7});


        Diary diary1 = new Diary();
        diary1.setTitle("aaa");
        diary1.setContent("ds");
        diary1.setCreateTime(new Date());
        diary1.setUserId(2);

        Diary diary2 = new Diary();
        diary2.setTitle("aaa");
        diary2.setContent("ds");

        Diary diary3 = new Diary();
        diary3.setContent("ds");
        diary3.setCreateTime(new Date());

        List<Diary> list = new ArrayList<>();
        list.add(diary1);
        list.add(diary2);
        list.add(diary3);

        diaryService.insertList(list);
    }
}
