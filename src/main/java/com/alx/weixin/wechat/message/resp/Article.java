package com.alx.weixin.wechat.message.resp;

import lombok.Data;

/**
 * ClassName: Article
 * @Description: 图文消息体
 * @author zhaoxny
 * @date 2018年8月28日 17:27:27
 */
@Data
public class Article {

    // 图文消息名称   
    private String Title;  
    // 图文消息描述   
    private String Description;  
    // 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80，
    private String PicUrl;  
    // 点击图文消息跳转链接   
    private String Url;

}