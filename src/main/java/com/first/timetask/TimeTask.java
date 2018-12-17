package com.first.timetask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class TimeTask {

    @Scheduled(cron = "0 0/1 * * * ?")
    @Async
    public void printABC(){
        System.out.println(new Date()+"ABC");
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    @Async
    public void printDEF(){
        System.out.println(new Date()+"DEF");
    }
}
