package com.self.sell.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.self.sell.modules.*.dao")
public class MybatisConfig {


}
