package com.demo.listener;

import com.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQConfig.TEST_QUEUE)
public class Rabbit {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("消息为  : " + hello);
    }
}
