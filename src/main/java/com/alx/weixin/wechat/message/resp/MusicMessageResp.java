package com.alx.weixin.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: MusicMessageResp
 * @Description: 音乐消息
 * @author dapengniao
 * @date 2016 年 3 月 7 日 下午 3:53:38
 */
@Data
public class MusicMessageResp extends BaseMessageResp {
    /**
     * 音乐
     */
    private Music Music;

}