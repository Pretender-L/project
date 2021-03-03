package com.project.demo.listener;

import com.project.demo.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = {RabbitConfig.FANOUT_QUEUE, RabbitConfig.DIRECT_QUEUE, RabbitConfig.TOPIC_QUEUE001, RabbitConfig.TOPIC_QUEUE002})
public class RabbitMQACKListener {
    /**
     * channel.basicAck
     * deliveryTag：该消息的index
     * multiple：是否批量. true：将一次性ack所有小于deliveryTag的消息。
     */
    @RabbitHandler
    public void process(Message message, Channel channel) {
        System.out.println("ReceiverA：" + new String(message.getBody()));
        try {
            //消费者成功处理后，调用channel.basicAck(message.getMessageProperties().getDeliveryTag(), false)方法对消息进行确认。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //channel.basicNack 与 channel.basicReject 的区别在于basicNack可以批量拒绝多条消息，而basicReject一次只能拒绝一条消息。

    /**
     * channel.basicNack
     * deliveryTag：该消息的index。
     * multiple：是否批量. true：将一次性拒绝所有小于deliveryTag的消息。
     * requeue：被拒绝的是否重新入队列。
     */
    @RabbitHandler
    public void processJsonMessage1(@Payload String body, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Message message, Channel channel) {
        System.out.println("ReceiverA：" + new String(message.getBody()));
        try {
            channel.basicNack(deliveryTag, true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deliveryTag：该消息的index。
     * requeue：被拒绝的是否重新入队列。
     */
    @RabbitHandler
    public void processJsonMessage2(@Payload String body, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Message message,Channel channel) {
        System.out.println("ReceiverA：" + new String(message.getBody()));
        try {
            channel.basicReject(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}