package com.alx.weixin.wechat.controller;

import com.alx.weixin.wechat.constant.WechatConstant;
import com.alx.weixin.wechat.dispatcher.EventDispatcher;
import com.alx.weixin.wechat.dispatcher.MsgDispatcher;
import com.alx.weixin.wechat.util.MessageUtil;
import com.alx.weixin.wechat.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @描述 微信开发者：接入指南
 * @创建人 zhaoxny
 * @创建时间 2018/8/28
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class WeChatController {


    /**
     * @param @param request
     * @param @param response
     * @param @param signature
     * @param @param timestamp
     * @param @param nonce
     * @param @param echostr
     * @Description: 用于接收 get 参数，返回验证参数
     * @author zhaoxny
     * 2018年8月28日 13:56:42
     */
    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error("错误{}", e);
        }
    }

    /**
     * 注意:
     * "application/json" json格式数据
     * "application/xml" xml类型数据
     * "application/x-www-form-urlencoded" 基本类型  jquery默认就是采用的这种
     * "multipart/form-data" 文件上传
     * "text/xml"  和application/xml采用的默认编码格式不一样
     * "text/html"  一般的html
     * */
    @RequestMapping(value = "/security", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    // post 方法用于接收微信服务端消息
    public void DoPost(HttpServletRequest request,HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        System.out.println("这是 post 方法！"+ WechatConstant.getAppId());
        try{
            String s = "";
            Map<String, String> map= MessageUtil.parseXml(request);
            System.out.println("============================="+map.toString());
            System.out.println("============================="+map.get("Content"));
            String msgtype = map.get("MsgType");

            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                s = EventDispatcher.processEvent(map); //进入事件处理
            }else{
                s = MsgDispatcher.processMessage(map);//进入消息处理
            }
            System.out.println("回复：：："+s);
            PrintWriter out = response.getWriter();
            out.write(s);
            out.close();
        }catch(Exception e){
            log.error("错误{}",e);
        }
    }
}
