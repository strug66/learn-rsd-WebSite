package com.rsd.day5;

import com.rsd.bean.Diary;
import com.rsd.service.IDiaryService;
import com.rsd.service.impl.DiaryServiceImpl;

import java.util.Date;
import java.util.List;

public class Test3_fengzhuang {
    public static void main(String[] args) {
        IDiaryService diaryService = new DiaryServiceImpl();

//        List<Diary> list = diaryService.queryList();
//        System.out.println(list);
//
//        Diary diary1 = diaryService.getById(6);
//        System.out.println(diary1);

        Diary diary = new Diary();
//        diary.setId(3);
//        diary.setTitle("aaa");
        diary.setContent("ds");
//        diary.setCreateTime("new Date()");
//        diary.setUserId(2);

        List<Diary> list = diaryService.queryListByParam(diary);

//        List<Diary> list = diaryService.queryListByIds("1, 4, 6");

        //select * from bu_diary where title = 'aaa' or 1=1;
        //select * from bu_diary where title = 'sss';
//        List<Diary> list = diaryService.queryListByTitle("'aaa' or 1=1");
        System.out.println(list);

//        diaryService.updateForNull(diary);
//        diaryService.update(diary); //更新个别字段

//        diaryService.insert(diary);

//        diaryService.deleteById(2);
    }
}
