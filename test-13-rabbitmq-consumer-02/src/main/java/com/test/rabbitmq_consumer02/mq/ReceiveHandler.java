package com.test.rabbitmq_consumer02.mq;

import com.rabbitmq.client.Channel;
import com.test.rabbitmq_consumer02.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 消息的消费者配置类
 */
@Configuration
public class ReceiveHandler {

    //监听email队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel) {
        System.out.println("接收到的邮件消息：" + msg);
    }

//    //监听sms队列
//    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
//    public void receive_sms(String msg, Message message, Channel channel) {
//        System.out.println("接收到的短信消息：" + msg);
//    }

}
