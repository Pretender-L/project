package com.project.demo.mq.rabbit;

import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
 */
public class ReturnCallBackHandler implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(@NotNull Message message, int replyCode, @NotNull String replyText, @NotNull String exchange, @NotNull String routingKey)  {
        System.out.println("消息主体 message："+message);
        System.out.println("应答码 replyCode: ："+replyCode);
        System.out.println("原因描述 replyText："+replyText);
        System.out.println("交换机 exchange："+exchange);
        System.out.println("消息使用的路由键 routingKey："+routingKey);
    }
}
