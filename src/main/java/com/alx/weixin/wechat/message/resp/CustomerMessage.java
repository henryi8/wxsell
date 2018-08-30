package com.alx.weixin.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: CustomerMessage
 * @Description: 客服消息接口
 * @author dapengniao
 * @date 2016 年 3 月 14 日 下午 6:28:08
 */
@Data
public class CustomerMessage {

    // 接收方帐号（收到的 OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;

}