package com.self.sell.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.sql.DataSource;

@ToString
@Getter
@Setter
class DbProperty {
    private String type;

    private String driverClassName;

    private String url;

    private String username;


    private String password;


    private String beanName;


    DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(getUrl());
        dataSource.setDriverClassName(getDriverClassName());
        dataSource.setUsername(getUsername());
        dataSource.setPassword(getPassword());
        return dataSource;
    }

    boolean isDefaultDb() {
        return "default".equals(getBeanName());
    }

}
