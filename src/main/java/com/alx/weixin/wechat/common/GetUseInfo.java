package com.alx.weixin.wechat.common;

import com.alibaba.fastjson.JSONObject;
import com.alx.weixin.wechat.util.HttpUtils;

import java.util.HashMap;

/**
 * ClassName: GetUseInfo
 * @Description: 获取微信用户信息
 * @author dapengniao
 * @date 2016 年 3 月 18 日 下午 2:00:52
 */
public class GetUseInfo {

    /**
     * @Description: 通过 openid 获取用户微信信息
     * @param @param openid
     * @param @return
     * @param @throws Exception   
     * @author dapengniao
     * @date 2016 年 3 月 18 日 下午 2:01:30
     */
    public static HashMap<String, String> userinfoByOpenid(String openid) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();

        String accessToken = WeChatTask.getAccessToken();
        String openidUserinfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken+"&openid="+openid+"&lang=zh_CN";

        //定时器中获取到的 token
        params.put("access_token", accessToken);
        params.put("openid", openid);  //需要获取的用户的 openid
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(openidUserinfoUrl, params);
        System.out.println(subscribers);
        params.clear();
        //这里返回参数只取了昵称、头像、和性别
        params.put("nickname", JSONObject.parseObject(subscribers).getString("nickname")); //昵称
        params.put("headimgurl", JSONObject.parseObject(subscribers).getString("headimgurl"));  //图像
        params.put("sex", JSONObject.parseObject(subscribers).getString("sex"));  //性别
        return params;
    }

}