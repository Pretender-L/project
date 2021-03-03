package com.project.demo.listener;

import com.project.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {RabbitConfig.FANOUT_QUEUE, RabbitConfig.DIRECT_QUEUE, RabbitConfig.TOPIC_QUEUE001, RabbitConfig.TOPIC_QUEUE002})
public class RabbitMQListener {
    @RabbitHandler
    public void process(String mes) {
        System.out.println("消息为  : " + mes);
    }
}
