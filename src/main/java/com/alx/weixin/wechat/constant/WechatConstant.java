package com.alx.weixin.wechat.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/29
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConstant {

    private static String appId;

    private static String appsecret;

    private static String tokenUrl;

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        WechatConstant.appId = appId;
    }

    public static String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        WechatConstant.appsecret = appsecret;
    }

    public static String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        WechatConstant.tokenUrl = tokenUrl;
    }
}
