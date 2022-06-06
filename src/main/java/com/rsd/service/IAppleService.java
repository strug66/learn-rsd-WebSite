package com.rsd.service;

import com.rsd.bean.Apple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAppleService {

    List<Apple> queryListByIdOrPrice(@Param("id") Integer id, @Param("price1") Integer price);

}
