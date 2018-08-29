package com.alx.weixin.wechat.dispatcher;

import com.alx.weixin.wechat.common.WeChatTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuartzJob{
    /**
     * @Description: 任务执行获取 token
     * @param    
     * @author dapengniao
     * @date 2016 年 3 月 10 日 下午 4:34:26
     */
    public void workForToken() {
        try {
            WeChatTask timer = new WeChatTask();
            timer.getToken_getTicket();
        } catch (Exception e) {
            log.error("{}",e);
        }
    }

}