package com.alx.weixin.wechat.common;

import com.alibaba.fastjson.JSONObject;
import com.alx.weixin.wechat.constant.WechatConstant;
import com.alx.weixin.wechat.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WeChatTask
 * @Description: 微信两小时定时任务体
 * @author dapengniao
 * @date 2016 年 3 月 10 日 下午 1:42:29
 */
public class WeChatTask {

    /**
     * @Description: 任务执行体
     * @param @throws Exception
     * @author dapengniao
     * @date 2016 年 3 月 10 日 下午 2:04:37
     */
    public void getToken_getTicket() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", WechatConstant.getAppId());
        params.put("secret", WechatConstant.getAppsecret());
        String jstoken = HttpUtils.sendGet(WechatConstant.getTokenUrl(), params);

        System.out.println(jstoken);

        String access_token = JSONObject.parseObject(jstoken).getString("access_token"); // 获取到 token 并赋值保存

        System.out.println("token 为==============="+access_token);
    }

}