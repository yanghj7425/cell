package com.self.sell.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.self.sell.common.pojo.bo.PageParam;
import com.self.sell.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public abstract class AbstractBaseService<T, M extends Mapper<T>> implements BaseService<T> {

    @Autowired
    private M baseMapper;


    @Override
    public Integer insertOne(T t) {
        return baseMapper.insert(t);
    }


    /**
     * @param list
     * @return int 数量
     * <pre>
     *
     * </pre>
     * TODO: 改为批量操作
     */
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


    protected List<T> queryListByExample(Example example) {
        return baseMapper.selectByExample(example);
    }


    protected final PageInfo<T> doQueryForPage(PageParam pageParam, Function<Map<String, Object>, List<T>> action) {
        try {
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            List<T> list = action.apply(pageParam.getParams());
            PageInfo<T> pageInfo = PageInfo.of(list);
            return pageInfo;
        } finally {
            PageHelper.clearPage();
        }
    }


    @Override
    public List<T> queryAll() {
        return baseMapper.selectAll();
    }

}
