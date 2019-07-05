package com.self.cell.common.service.impl;

import com.self.cell.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Slf4j
public abstract class AbstractBaseService<T, M extends Mapper<T>> implements BaseService<T> {

    @Autowired
    private M baseMapper;

    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

    @Override
    public Integer insertOne(T t) {
        return baseMapper.insert(t);
    }
}
