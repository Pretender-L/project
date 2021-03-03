package com.project.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String FANOUT_QUEUE = "fanout_queue";
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String TOPIC_QUEUE001 = "topic_queue001";
    public static final String TOPIC_QUEUE002 = "topic_queue002";


    /**
     * 队列
     */
    @Bean(FANOUT_QUEUE)
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE);
    }

    @Bean(DIRECT_QUEUE)
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    @Bean(TOPIC_QUEUE001)
    public Queue topicQueue001() {
        return new Queue(TOPIC_QUEUE001);
    }

    @Bean(TOPIC_QUEUE002)
    public Queue topicQueue002() {
        return new Queue(TOPIC_QUEUE002);
    }

    /**
     * 广播交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
     */
    @Bean(FANOUT_EXCHANGE)
    public Exchange fanoutEx() {
        return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).build();
    }

    /**
     * 直连交换机
     */
    @Bean(DIRECT_EXCHANGE)
    public Exchange directEx() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE).build();
    }

    /***
     * Topic Exchange
     * 主题交换机(通配符交换机)，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
     * 简单地介绍下规则：
     * *(星号) 用来表示一个单词 (必须出现的)
     * #(井号) 用来表示任意数量（零个或多个）单词
     * 通配的绑定键是跟队列进行绑定的，举个小例子
     * 队列Q1 绑定键为 *.TT.*     队列Q2绑定键为 TT.#
     * 如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
     * 如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；
     */
    @Bean(TOPIC_EXCHANGE)
    public Exchange topicEx() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).build();
    }

    /**
     * 绑定规则
     */
    @Bean
    public Binding fanoutExBinding(@Qualifier(FANOUT_QUEUE) Queue queue, @Qualifier(FANOUT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding directExBinding(@Qualifier(DIRECT_QUEUE) Queue queue, @Qualifier(DIRECT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct").noargs();
    }

    @Bean
    public Binding topicExBinding001(@Qualifier(TOPIC_QUEUE001) Queue queue, @Qualifier(TOPIC_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topics.#").noargs();
    }

    @Bean
    public Binding topicExBinding002(@Qualifier(TOPIC_QUEUE002) Queue queue, @Qualifier(TOPIC_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.*").noargs();
    }
}
