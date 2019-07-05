package com.self.cell.common.service;

import java.util.List;

public interface BaseService<T> {


    List<T> queryAll();

    Integer insertOne(T t);

}
