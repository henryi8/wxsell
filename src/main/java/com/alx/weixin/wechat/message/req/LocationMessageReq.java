package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: LocationMessageReq
 * @Description: 地理位置消息
 * @author zhaoxy
 * @date 2018年8月28日 16:09:15
 */
@Data
public class LocationMessageReq extends BaseMessageReq {
    /**
     * 地理位置维度
      */
    private String Location_X;
    /**
     * 地理位置经度
      */
    private String Location_Y;
    /**
     * 地图缩放大小
      */
    private String Scale;
    /**
     * 地理位置信息
      */
    private String Label;

}