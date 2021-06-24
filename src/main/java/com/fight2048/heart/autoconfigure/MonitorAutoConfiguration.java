package com.fight2048.heart.autoconfigure;

import com.fight2048.heart.HttpUtils;
import com.fight2048.heart.MonitorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2021-05-22 0022 下午 9:44
 * @version: v0.0.0
 * @description: 心跳体系
 */
@Configuration
@EnableConfigurationProperties(MonitorProperties.class)
@EnableScheduling //开启定时任务
public class MonitorAutoConfiguration {
    private Logger log = LoggerFactory.getLogger(MonitorAutoConfiguration.class);

    @Value("${server.port}")
    public String port;

    @Autowired
    private MonitorProperties properties;

//    @Bean
//    @ConditionalOnMissingBean
//    public String aliyunSmsTemplate(HeartProperties properties) {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//                HashMap hashMap= new HashMap<>();
//                hashMap.put("Content-Type","application/json;charset=UTF-8");//?port=8081&projectId=123456
//                HttpUtils.doPutHttpRequest("http://iam.turingthink.com:8901/turing/common/v1/services/?port="+port+"&projectId="+properties.getBeat().getId(),hashMap,properties.getBeat().getId());
//                System.out.println("-------心跳--------");
//            }
//        }, 0,300000);// 设定指定的时间time,此处为五分钟
//        return "";
//    }

    @Scheduled(cron = "0 */1 * * * ? ")
    public void configureTasks() {
        if (!properties.getHeartbeatEnable()) {
            return;
        }

        if (Objects.isNull(properties.getHeartbeatUrl())) {
            return;
        }

        log.info("开始心跳---------每5分钟上传一次心跳");
        HashMap hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json;charset=UTF-8");//?port=8081&projectId=123456

        StringBuilder sb = new StringBuilder();
        sb.append(properties.getHeartbeatUrl())
                .append("?port=")
                .append(port)
                .append("&projectId=")
                .append(properties.getProjectId());

        HttpUtils.doPutHttpRequest(sb.toString(), hashMap, properties.getProjectId());
        log.info("结束心跳---------每5分钟上传一次心跳");
    }
}
