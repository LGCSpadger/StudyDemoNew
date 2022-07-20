package com.test.pub;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@MapperScan("com.test.pub.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class TestPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPublicApplication.class,args);
    }

    //okhttp 配置
    @Bean
    public RestTemplate restTemplate() {
        OkHttp3ClientHttpRequestFactory httpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(50000);//设置请求连接超时时间
        httpRequestFactory.setReadTimeout(600000);//设置数据读取超时时间
        return new RestTemplate(httpRequestFactory);
    }

}
