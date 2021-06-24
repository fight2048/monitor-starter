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
@ConfigurationProperties(prefix = MonitorProperties.ID_PREFIX)
public class MonitorProperties {
    public static final String ID_PREFIX = "monitor";

    private String projectId;

    private Boolean heartbeatEnable = true;

    private String heartbeatUrl;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Boolean getHeartbeatEnable() {
        return heartbeatEnable;
    }

    public void setHeartbeatEnable(Boolean heartbeatEnable) {
        this.heartbeatEnable = heartbeatEnable;
    }

    public String getHeartbeatUrl() {
        return heartbeatUrl;
    }

    public void setHeartbeatUrl(String heartbeatUrl) {
        this.heartbeatUrl = heartbeatUrl;
    }
}
