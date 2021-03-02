package com.project.demo.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
//定时任务注解
@EnableScheduling//可以在启动类上注解也可以在当前文件
public class TimingTask {
    private int hour = 0;
    private int minute = 0;
    private int second = 0;

    @Scheduled(cron = "0/1 * * * * ?")//1秒执行一次
    public void runFirst() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dt = simpleDateFormat.format(date);
        if (dt.endsWith("0:00")) {
            System.out.println("当前时间为" + dt);
        }
        if ((minute == 0 || minute == 10 || minute == 20 || minute == 30 || minute == 40 || minute == 50) && second == 0) {
            if (hour == 0 && minute == 0) {
                System.out.println("程序计时器已启动");
            } else {
                System.out.println("程序已运行" + hour + "时" + minute + "分" + second + "秒");
            }
        }
        second += 1;
        if (second == 60) {
            minute += 1;
            second = 0;
        }
        if (minute == 60) {
            hour += 1;
            minute = 0;
        }
    }
}
