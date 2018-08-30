package com.alx.weixin.wechat.dispatcher;

import com.alx.weixin.wechat.common.GetUseInfo;
import com.alx.weixin.wechat.message.resp.Article;
import com.alx.weixin.wechat.message.resp.NewsMessageResp;
import com.alx.weixin.wechat.util.MessageUtil;

import java.util.*;

/**
 * ClassName: EventDispatcher
 *
 * @author dapengniao
 * @Description: 事件消息业务分发器
 * @date 2016 年 3 月 7 日 下午 4:04:41
 */
public class EventDispatcher {

    public static String processEvent(Map<String, String> map) {
        String openid = map.get("FromUserName"); // 用户 openid
        String mpid = map.get("ToUserName"); // 公众号原始 ID


        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");

//            ImageMessageResp imgmsg = new ImageMessageResp();
//            imgmsg.setToUserName(openid);
//            imgmsg.setFromUserName(mpid);
//            imgmsg.setCreateTime(new Date().getTime());
//            imgmsg.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
//            Image img = new Image();
//            HttpPostUploadUtil util=new HttpPostUploadUtil();
//            String filepath="D:\\1.jpg";
//            Map<String, String> textMap = new HashMap<String, String>();
//            textMap.put("name", "testname");
//            Map<String, String> fileMap = new HashMap<String, String>();
//            fileMap.put("userfile", filepath);
//            String mediaidrs = util.formUpload(textMap, fileMap);
//            System.out.println("上传文件地址-----"+mediaidrs);
//            String mediaid= JSONObject.parseObject(mediaidrs).getString("media_id");
//            img.setMediaId(mediaid);
//            imgmsg.setImage(img);
//            return MessageUtil.imageMessageRespToXml(imgmsg);

            //对图文消息
            NewsMessageResp newmsg=new NewsMessageResp();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(new Date().getTime());
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            try {
                HashMap<String, String> userinfo= GetUseInfo.userinfoByOpenid(openid);
                Article article=new Article();
                article.setDescription("欢迎来到博客：菜鸟程序员成长之路！"); //图文消息的描述
                article.setPicUrl(userinfo.get("headimgurl")); //图文消息图片地址
                article.setTitle("尊敬的："+userinfo.get("nickname")+","+
                        (userinfo.get("sex").equals("1")?"男":"女")
                        +",你好！");  //图文消息标题
                article.setUrl("http://www.baidu.com");  //图文 url 链接
                List<Article> list=new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageRespToXml(newmsg);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("====代码有问题额☺！");
            }

        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
            return "";
        }

//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
//            System.out.println("==============这是扫描二维码事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
//            System.out.println("==============这是位置上报事件！");
//        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
        }

//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
//            System.out.println("==============这是自定义菜单 View 事件！");
//        }

        return null;
    }
}