package com.rsd.day4;

import java.util.List;

public interface IService<E> {
    void insert(E e);

    void update(E e);

    void delete(Integer id);

    List<E> queryList();

    E querryById(Integer id);
}
