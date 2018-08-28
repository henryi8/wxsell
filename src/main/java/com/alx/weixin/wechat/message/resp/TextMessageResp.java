package com.alx.weixin.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: TextMessageReq
 * @Description: 文本消息
 * @author zhaoxny
 * @date 2018年8月28日 16:10:24
 */
@Data
public class TextMessageResp extends BaseMessageResp {
    /**
     * 回复的消息内容
      */
    private String Content;

}