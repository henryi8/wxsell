package com.alx.weixin.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: BaseMessageReq
 * @Description: 返回消息体-基本消息
 * @author zhaoxny
 * @date 2018年8月28日 16:07:13
 */
@Data
public class BaseMessageResp {

    /**
     * 接收方帐号（收到的 OpenID）
      */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息类型（text/music/news）
     */
    private String MsgType;

}