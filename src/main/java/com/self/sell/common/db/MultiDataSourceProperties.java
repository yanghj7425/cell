package com.self.sell.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(
        prefix = "spring.multi-datasource"
)
@ToString
public class MultiDataSourceProperties {

    private List<DbProperty> dbProperties;

    private DataSource defaultDataSource;

    private Map<Object, Object> defineMultiDataSource;

    @Bean
    public MultiDataSource multiDataSource() {
        init();
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setDefaultTargetDataSource(defaultDataSource);
        multiDataSource.setTargetDataSources(defineMultiDataSource);
        return multiDataSource;
    }

    private void init() {
        if (dbProperties.isEmpty()) {
            throw new IllegalStateException("【数据源配置】 读取不到数据源信息, dbProperties 不能为空");
        }
        for (DbProperty dbProperty : dbProperties) {
            if (isDefaultDb(dbProperty)) {
                defaultDataSource = getDataSource(dbProperty);
                continue;
            }
            defineMultiDataSource.put(dbProperty.getBeanName(), getDataSource(dbProperty));

        }

    }

    private boolean isDefaultDb(DbProperty dbProperty) {
        return "default".equals(dbProperty.getBeanName()) && defaultDataSource == null;

    }


    private DataSource getDataSource(DbProperty dbProperty) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbProperty.getUrl());
        dataSource.setDriverClassName(dbProperty.getDriverClassName());
        dataSource.setUsername(dbProperty.getUsername());
        dataSource.setPassword(dbProperty.getPassword());
        return dataSource;
    }


    public void setDbProperties(List<DbProperty> dbProperties) {
        this.dbProperties = dbProperties;
    }

}
