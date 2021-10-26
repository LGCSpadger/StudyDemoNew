package com.test.rabbitmq_consumer02.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 消息消费者配置类
 */
@Configuration
public class RabbitmqConfig {

    //定义队列
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";//邮件队列
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";//短信队列

    //定义交换机
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";//短信、邮件通知交换机

    /**
     * 配置交换机
     * ExchangeBuilder提供了fanout、direct、topic、header交换机类型的配置
     * @return
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        ////durable(true)持久化，消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    //声明邮件队列
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    //声明短信队列
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        return new Queue(QUEUE_INFORM_SMS);
    }

    /**
     * 队列绑定到交换机（邮件队列绑定到交换机）
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        //在 with() 中指定 routingKey ，routingKey 中的 # 号表示 通配符，可以匹配任何字符
        return BindingBuilder.bind(queue).to(exchange).with("inform.pdzdh.email.#").noargs();
    }

    /**
     * 队列绑定到交换机（短信队列绑定到交换机）
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                              @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        //在 with() 中指定 routingKey ，routingKey 中的 # 号表示 通配符，可以匹配任何字符
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.sms.#").noargs();
    }

}
