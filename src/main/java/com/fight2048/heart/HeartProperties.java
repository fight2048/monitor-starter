package com.fight2048.heart;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2021-05-22 0022 下午 9:44
 * @version: v0.0.0
 * @description:
 */
@ConfigurationProperties(prefix = HeartProperties.ID_PREFIX)
public class HeartProperties {
    public static final String ID_PREFIX = "heart";

    private HeartBeatProperties beat = new HeartBeatProperties();

    public HeartBeatProperties getBeat() {
        return beat;
    }

    public void setBeat(HeartBeatProperties beat) {
        this.beat = beat;
    }

    public static class HeartBeatProperties {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
