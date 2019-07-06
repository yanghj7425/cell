package com.self.cell.common.service;

import java.util.List;

public interface BaseService<T> {

    Integer insertOne(T t);

    Integer insertList(List<T> list);

    Integer deleteOneById(Long id);

    Integer deleteByWhere(T t);

    Integer updateSelectiveById(T t);

    Integer updateSelectiveByProperty(T t, String property, List<Object> values);

    T queryOneById(Long id);

    List<T> queryAll();


}
