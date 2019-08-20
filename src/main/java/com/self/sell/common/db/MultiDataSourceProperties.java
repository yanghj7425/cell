package com.self.sell.common.db;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(
        prefix = "spring.multi-datasource"
)
@ToString
public class MultiDataSourceProperties {

    private List<DbProperty> dbProperties;

    @Bean
    public MultiDataSource multiDataSource() {
        return new MultiDataSource.Builder(dbProperties).builder();
    }


    public void setDbProperties(List<DbProperty> dbProperties) {
        this.dbProperties = dbProperties;
    }

}
