package com.rsd.mapper;

import com.rsd.bean.Apple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAppleMapper {
    List<Apple> queryList();

    Apple getById(Integer id);

    List<Apple> queryListByIdOrPrice(@Param("id") Integer id, @Param("price1") Integer price);

    void insert(Apple apple);

    void update(Apple apple);

    void delete(Integer id);
}
