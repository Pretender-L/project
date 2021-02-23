package com.project;

import com.project.task.TreadTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadPoolTaskTest {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private TreadTasks treadTasks;

    /**
     * 轮流执行测试成功,获得返回值测试成功
     */
    @Test
    public void threadPoolTest1() throws InterruptedException {
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println("异步线程"+i);
                }
                return "返回值";
            }
        };
        //返回值(需要返回值用submit)
        Future<Object> future = threadPoolTaskExecutor.submit(callable);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("主线程"+i);
        }
        //获取返回值会阻塞当前线程
        try {
            System.out.println("返回值为:"+future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 轮流执行测试成功
     */
    @Test
    public void threadPoolTest2() throws InterruptedException {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("异步线程");
                }
            }
        };
        //不需要返回值用execute
        threadPoolTaskExecutor.execute(myRunnable);
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println("主线程");
        }
    }

    /**
     * 轮流执行测试成功
     */
    @Test
    public void asyncTest() throws InterruptedException {
        treadTasks.startMyTreadTask();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("主线程");
        }
    }
}

