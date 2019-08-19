package com.self.sell.common.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class MultiDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();


    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
