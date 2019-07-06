package com.self.cell.common.service.impl;

import com.self.cell.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
public abstract class AbstractBaseService<T, M extends Mapper<T>> implements BaseService<T> {

    @Autowired
    private M baseMapper;


    @Override
    public Integer insertOne(T t) {
        return baseMapper.insert(t);
    }


    @Override
    public Integer insertList(List<T> list) {
        Long count = list.stream().map(this::insertOne).count();
        return count.intValue();
    }

    @Override
    public Integer deleteOneById(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByWhere(T t) {
        return baseMapper.delete(t);
    }

    @Override
    public Integer updateSelectiveById(T t) {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public Integer updateSelectiveByProperty(T t, String property, List<Object> values) {
        Example example = new Example(t.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(property, values);
        return baseMapper.updateByExampleSelective(t, example);
    }

    @Override
    public T queryOneById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<T> queryListByProperty(Class<T> clazz, String property, List<Object> values) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(property, values);
        return baseMapper.selectByExample(example);
    }


    @Override
    public List<T> queryListByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

}
