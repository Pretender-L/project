package com.demo;

import com.demo.config.RabbitMQConfig;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void rabbitMQTest() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_EXCHANGE, "", "测试");
    }

}
