package com.project.demo;

import com.project.demo.config.RabbitMQConfig;
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
    public void rabbitMQTest1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", "广播交换机测试");
    }

    @Test
    public void rabbitMQTest2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, "direct", "直连交换机测试");
    }

    @Test
    public void rabbitMQTest3() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topics.test.mes", "通配符交换机测试001");
    }

    @Test
    public void rabbitMQTest4() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.test", "通配符交换机测试002");
    }
}
