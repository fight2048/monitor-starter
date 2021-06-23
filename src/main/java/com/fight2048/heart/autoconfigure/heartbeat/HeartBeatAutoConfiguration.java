package com.fight2048.heart.autoconfigure.heartbeat;

import com.fight2048.heart.HttpUtils;
import com.fight2048.heart.HeartProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2021-05-22 0022 下午 9:44
 * @version: v0.0.0
 * @description: 心跳体系
 */
@Configuration
@EnableConfigurationProperties(HeartProperties.class)
@EnableScheduling //开启定时任务
public class HeartBeatAutoConfiguration {
    @Value("${server.port}")
    public String port;

    @Bean
    @ConditionalOnMissingBean
    public String aliyunSmsTemplate(HeartProperties properties) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                HashMap hashMap= new HashMap<>();
                hashMap.put("Content-Type","application/json;charset=UTF-8");//?port=8081&projectId=123456
                HttpUtils.doPutHttpRequest("http://iam.turingthink.com:8901/turing/common/v1/services/?port="+port+"&projectId="+properties.getBeat().getId(),hashMap,properties.getBeat().getId());
                System.out.println("-------心跳--------");
            }
        }, 0,300000);// 设定指定的时间time,此处为五分钟
        return "";
    }
}
