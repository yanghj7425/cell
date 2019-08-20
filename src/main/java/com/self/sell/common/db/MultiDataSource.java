package com.self.sell.common.db;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.List;
import java.util.Map;

@Slf4j
public class MultiDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();


    private MultiDataSource() {
    }


    void setDefaultTargetDataSource(DbProperty property) {
        if (property.isDefaultDb()) {
            super.setDefaultTargetDataSource(property.getDataSource());
        }
    }

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }


    static class Builder {
        private List<DbProperty> dbProperties;

        Builder(List<DbProperty> dbProperties) {
            this.dbProperties = dbProperties;
        }

        MultiDataSource builder() {
            MultiDataSource multiDataSource = new MultiDataSource();
            Map<Object, Object> multiDataSourceMap;
            if (dbProperties.isEmpty()) {
                throw new IllegalStateException("【数据源配置】 读取不到数据源信息, dbProperties 不能为空");
            }
            multiDataSourceMap = MapUtil.newHashMap();
            for (DbProperty dbProperty : dbProperties) {
                multiDataSource.setDefaultTargetDataSource(dbProperty);
                multiDataSourceMap.put(dbProperty.getBeanName(), dbProperty.getDataSource());
            }
            multiDataSource.setTargetDataSources(multiDataSourceMap);
            return multiDataSource;
        }

    }


}
