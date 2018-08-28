package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: BaseMessageReq
 * @Description: 微信请求消息基本类
 * @author zhaoxny
 * @date 2018年8月28日 16:07:13
 */
@Data
public class BaseMessageReq {

    /**
     * 开发者微信号
      */
    private String ToUserName;
    /**
     * 发送方帐号（一个 OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息类型（text/image/location/link/video/shortvideo）
     */
    private String MsgType;
    /**
     *  消息 id，64 位整型
     */
    private long MsgId;

}