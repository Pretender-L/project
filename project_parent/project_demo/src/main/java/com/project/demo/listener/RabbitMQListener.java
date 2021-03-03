package com.project.demo.listener;

import com.project.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {RabbitMQConfig.FANOUT_QUEUE,RabbitMQConfig.DIRECT_QUEUE,RabbitMQConfig.TOPIC_QUEUE001,RabbitMQConfig.TOPIC_QUEUE002})
public class RabbitMQListener {
    @RabbitHandler
    public void process(String mes) {
        System.out.println("消息为  : " + mes);
    }
}
