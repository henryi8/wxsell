package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: LinkMessageReq
 * @Description: 连接消息
 * @author zhaoxny
 * @date 2018年8月28日 16:08:08
 */
@Data
public class LinkMessageReq extends BaseMessageReq {
    /**
     * 消息标题
      */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;

}