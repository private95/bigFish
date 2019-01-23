package com.egoo.seckill.bigfish.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class BigFishJob {

    @Scheduled(cron = "59 * * * * ?")
    public void boom(){
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date() ));
    }
}
