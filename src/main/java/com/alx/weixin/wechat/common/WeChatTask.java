package com.alx.weixin.wechat.common;

import com.alibaba.fastjson.JSONObject;
import com.alx.weixin.wechat.constant.WechatConstant;
import com.alx.weixin.wechat.util.HttpUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WeChatTask
 * @Description: 微信两小时定时任务体(获取公众号的 access_token)
 * @author dapengniao
 * @date 2016 年 3 月 10 日 下午 1:42:29
 */
public class WeChatTask {

    /**
     * @Description: 任务执行体  获取token
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

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("access_token",access_token);

        System.out.println("token 为==============="+access_token);
    }

    /**
     * 从session中取出token
     * @return
     */
    public static String getAccessToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        return access_token;
    }

}