package com.self.sell.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.self.sell.modules.*.dao")
public class MybatisConfig {

//
//    @Value("${spring.mul-datasource.db-properties[0].driver-class-name}")
//    private String driverName;
//
//    private String url = "jdbc:mysql://127.0.0.1:3306/mini?useUnicode=true&characterEncoding=utf8&useSSL=true&allowMultiQueries=true&serverTimezone=UTC";
//
//    @Value("${spring.mul-datasource.db-properties[0].username}")
//    private String userName;
//
//
//    @Value("${spring.mul-datasource.db-properties[0].password}")
//    private String password;
//
//
//    @Value("${spring.mul-datasource.db-properties[0].url}")
//    private String defaultUrl;
//
//
//
//    private DataSource defaultDataSource() {
//        return getDataSource(defaultUrl);
//    }
//
//    private DataSource dataSource() {
//        return getDataSource(url);
//    }
//
//    private DataSource getDataSource(String url) {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driverName);
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//
//    @Autowired
//    private MultiDataSourceProperties multiDataSourceProperties;
//
//    @Bean
//    public MultiDataSource multiDataSource() {
//
//        System.out.println(multiDataSourceProperties.toString());
//
//        MultiDataSource multiDataSource = new MultiDataSource();
//        Map<Object, Object> map = MapUtil.newHashMap();
//        multiDataSource.setDefaultTargetDataSource(defaultDataSource());
//
//        map.put("dataSource1", dataSource());
//        multiDataSource.setTargetDataSources(map);
//        return multiDataSource;
//    }


}
