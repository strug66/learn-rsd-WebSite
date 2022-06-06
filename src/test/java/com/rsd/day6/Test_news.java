package com.rsd.day6;

import com.rsd.bean.NewsInfo;
import com.rsd.service.INewsInfoService;
import com.rsd.service.INewsTypeService;
import com.rsd.service.impl.NewsInfoServiceImpl;
import com.rsd.service.impl.NewsTypeServiceImpl;

import java.util.Date;

public class Test_news {
    public static void main(String[] args) {
//        INewsTypeService newsTypeService = new NewsTypeServiceImpl();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
//
//        INewsTypeService newsTypeService2 = new NewsTypeServiceImpl();
//        newsTypeService2.queryList();

        INewsInfoService newsInfoService = new NewsInfoServiceImpl();

//        NewsInfo newsInfo1 = newsInfoService.getById(12);
//        System.out.println(newsInfo1);

        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setId(1);
        newsInfo.setTitle("大朋友-习近平的牵挂");
        newsInfo.setTypeId(4);
        newsInfo.setShortDesc("习言道 [最温柔的牵挂]");
        newsInfo.setContent("'自古英雄出少年，长江后浪推前浪。'在孩子们心中，他不仅是和蔼可亲的'习爷爷'，也是睿智博学的'大朋友'。作为儿童成长的'领路人'，习近平总书记关心关爱少年儿童成长成才，以最温柔的牵挂送给了孩子们一份份珍贵的人生礼物。");
        newsInfo.setShowTime("2022-06-01 00:00:00");
        newsInfo.setPubUserId(6);
        newsInfo.setCreateTime(new Date());
        newsInfo.setUpdateTime(new Date());

//        newsInfoService.insert(newsInfo);
//        newsInfoService.update(newsInfo);
//        newsInfoService.delete(4);

//        newsInfoService.queryList();
    }
}
