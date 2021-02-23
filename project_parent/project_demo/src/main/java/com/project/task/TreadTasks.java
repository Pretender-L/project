package com.project.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TreadTasks {
    @Async
    public void startMyTreadTask() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000L);
            System.out.println("异步执行");
        }
    }
}
