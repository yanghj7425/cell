package com.self.sell.modules.user.service;

import com.self.sell.common.service.BaseService;
import com.self.sell.modules.user.entity.SysUser;

public interface UserService extends BaseService<SysUser> {

    String getATestString();

}
