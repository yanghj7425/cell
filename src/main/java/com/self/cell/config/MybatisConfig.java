package com.self.cell.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.self.cell.*.dao")
public class MybatisConfig {
}
