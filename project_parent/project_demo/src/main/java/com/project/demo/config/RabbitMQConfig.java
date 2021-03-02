package com.project.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String TEST_EXCHANGE = "test_exchange";
    public static final String TEST_QUEUE = "test_queue";

    /**
     * 队列
     */
    @Bean(TEST_QUEUE)
    public Queue testQueue() {
        return new Queue(TEST_QUEUE);
    }

    /**
     * 广播交换机
     */
    @Bean(TEST_EXCHANGE)
    public Exchange testEx() {
        return ExchangeBuilder.fanoutExchange(TEST_EXCHANGE).build();
    }

    /**
     * 绑定规则
     */
    @Bean
    public Binding testExBinding(@Qualifier(TEST_QUEUE) Queue queue, @Qualifier(TEST_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
