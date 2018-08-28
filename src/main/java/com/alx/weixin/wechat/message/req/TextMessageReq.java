package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: TextMessageReq
 * @Description: 文本消息
 * @author zhaoxny
 * @date 2018年8月28日 16:10:24
 */
@Data
public class TextMessageReq extends BaseMessageReq {
    /**
     * 消息内容
      */
    private String Content;

}