package com.alx.weixin.wechat.message.req;

import lombok.Data;

/**
 * ClassName: VideoMessageReq
 * @Description: 视频/小视屏消息
 * @author zhaoxny
 * @date 2018年8月28日 16:11:26
 */
@Data
public class VideoMessageReq extends BaseMessageReq {

    /**
     * 视频消息媒体 id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;
    /**
     * 视频消息缩略图的媒体 id，可以调用多媒体文件下载接口拉取数据
     */
    private String ThumbMediaId;

}