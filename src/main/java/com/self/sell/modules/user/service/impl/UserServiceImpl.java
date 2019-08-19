package com.self.sell.modules.user.service.impl;

import com.self.sell.common.db.aop.annotation.DynamicRoutingDataSource;
import com.self.sell.common.service.impl.AbstractBaseService;
import com.self.sell.modules.user.entity.SysUser;
import com.self.sell.modules.user.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<SysUser, Mapper<SysUser>> implements UserService {


    @Override
    @DynamicRoutingDataSource(name = "dataSource")
    public String getATestString() {
        return "This Is You Wanna 【 String 】";
    }


    @Override
    @DynamicRoutingDataSource(name = "dataSource1")
    public List<SysUser> queryAll() {
        return super.queryAll();
    }
}
