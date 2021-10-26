package com.test.rabbitmq_producer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 在 Springboot 中使用多数据源的时候，需要在启动类注解 @SpringBootApplication 后加上 (exclude = DruidDataSourceAutoConfigure.class)，否则会报错 Failed to determine a suitable driver class
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class RabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class,args);
    }

    //配置 RabbitTemplate，通过 RabbitTemplate 发送消息
//    @Bean
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
//        return rabbitTemplate;
//    }

}
