package com.discord.boot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveService {

    @Scheduled(fixedRate = 1*100*600)
    public void reportCurrentTime(){
        System.out.println(System.currentTimeMillis());
    }
}
