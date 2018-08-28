package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: ImageMessageReq
 *
 * @author zhaoxy
 * @Description: 图片消息
 * @date 2018年8月28日 16:07:32
 */
@Data
public class ImageMessageReq extends BaseMessageReq {
    /**
     * 图片链接
     */
    private String PicUrl;

}