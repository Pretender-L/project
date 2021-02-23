package com.project.user.listener;

import com.alibaba.fastjson.JSON;
import com.project.order.pojo.Task;
import com.project.user.config.RabbitMQConfig;

import com.project.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddPointListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.CG_BUYING_ADDPOINT)
    public void receiveAddPointLMessage(String message) {
        System.out.println("接收到的消息为：" + message);
        //转换消息
        Task task = JSON.parseObject(message, Task.class);
        if (task == null || StringUtils.isEmpty(task.getRequestBody())) {
            Object value = redisTemplate.boundValueOps(task.getId()).get();
            if (value!=null){
                return;
            }
        }
        int result = userService.updateUserPoint(task);
        if (result==0){
            return;
        }
        rabbitTemplate.convertAndSend(RabbitMQConfig.EX_BUYING_ADDPOINTUSER,RabbitMQConfig.CG_BUYING_FINISHADDPOINT_KEY,JSON.toJSONString(task));
    }

}
