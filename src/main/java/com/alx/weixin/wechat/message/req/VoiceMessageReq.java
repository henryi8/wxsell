package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: VoiceMessageReq
 * @Description: 语音消息
 * @author zhaoxny
 * @date 2018年8月28日 16:12:40
 */
@Data
public class VoiceMessageReq extends BaseMessageReq {
    /**
     * 媒体 ID
      */
    private String MediaId;
    /**
     * 语音格式
      */
    private String Format;
}