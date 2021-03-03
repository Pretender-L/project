package com.project.demo.mq.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "confirm_queue_B")
public class AckTempalte {
    enum Action{
        ACCEPT, // 处理成功
        RETRY, // 可以重试的错误
        REJECT, // 无需重试的错误
    }
    @RabbitHandler
    public void processJsonUser(Message message, Channel channel){
        Action action=Action.ACCEPT;
        long tag=message.getMessageProperties().getDeliveryTag();
        try{

            message.getMessageProperties().getConsumerTag();
            System.out.println( message.getMessageProperties().getConsumerTag());
            String message1 = new String(message.getBody(), "UTF-8");
            System.out.println("获取消息'" + message1 + "'");

        }catch (Exception e){
            // 根据异常种类决定是ACCEPT、RETRY还是 REJECT
            action = Action.RETRY;
            e.printStackTrace();

        }finally {
            try {
                // 通过finally块来保证Ack/Nack会且只会执行一次
                if (action == Action.ACCEPT) {
                    channel.basicAck(tag, true);
                    // 重试
                } else if (action == Action.RETRY) {
                    channel.basicNack(tag, false, true);
                    Thread.sleep(2000L);
                    // 拒绝消息也相当于主动删除mq队列的消息
                } else {
                    channel.basicNack(tag, false, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
