package com.alx.weixin.wechat.dispatcher;

import com.alx.weixin.wechat.message.resp.Article;
import com.alx.weixin.wechat.message.resp.NewsMessageResp;
import com.alx.weixin.wechat.message.resp.TextMessageResp;
import com.alx.weixin.wechat.util.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: MsgDispatcher
 *
 * @author dapengniao
 * @Description: 消息业务处理分发器
 * @date 2018年8月28日 17:50:52
 */
public class MsgDispatcher {

    public static String processMessage(Map<String, String> map) {
        String openid=map.get("FromUserName"); //用户 openid
        String mpid=map.get("ToUserName");   //公众号原始 ID

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");

            //普通文本消息
            TextMessageResp txtmsg=new TextMessageResp();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            txtmsg.setContent("1213：小仙女，我喜欢你！");
            return MessageUtil.textMessageRespToXml(txtmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");

            //对图文消息
            NewsMessageResp newmsg=new NewsMessageResp();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(new Date().getTime());
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            Article article=new Article();
            article.setDescription("这是图文消息 1"); //图文消息的描述
            article.setPicUrl("https://i1.mifile.cn/f/i/2018/mi8/summary/index1.jpg"); //图文消息图片地址
            article.setTitle("图文消息 1");  //图文消息标题
            article.setUrl("http://www.baidu.com");  //图文 url 链接
            List<Article> list=new ArrayList<>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
            newmsg.setArticleCount(list.size());
            newmsg.setArticles(list);
            return MessageUtil.newsMessageRespToXml(newmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}