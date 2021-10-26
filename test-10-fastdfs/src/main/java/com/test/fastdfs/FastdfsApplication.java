package com.test.fastdfs;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在 Springboot 中使用多数据源的时候，需要在启动类注解 @SpringBootApplication 后加上 (exclude = DruidDataSourceAutoConfigure.class)，否则会报错 Failed to determine a suitable driver class
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class FastdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsApplication.class,args);
    }

}
