package com.self.sell.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@Slf4j
public class ShiroConfig {


    @Autowired
    public void setShiroConfiguration(@Value("classpath:shiro.ini") final Resource resource) {
        try {
            if (resource.exists()) {
                final String location = resource.getURI().toString();
                //初始化SecurityManager对象
                Factory<SecurityManager> factory = new IniSecurityManagerFactory(location);

                //通过SecurityManager工厂对象,获取SecurityManager实例对象.
                SecurityManager securityManager = factory.getInstance();

                // 把 securityManager 实例 绑定到 SecurityUtils
                SecurityUtils.setSecurityManager(securityManager);
            } else {
                log.debug("Shiro configuration is not defined");
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


}
