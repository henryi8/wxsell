package com.alx.weixin.wechat.message.resp;

import lombok.Data;

import java.util.List;

/**
 * ClassName: NewsMessageResp
 * @Description: 多图文消息
 * @author dapengniao
 * @date 2016 年 3 月 7 日 下午 3:52:18
 */
@Data
public class NewsMessageResp extends BaseMessageResp {
    /**
     * 图文消息个数，限制为 10 条以内
      */
    private int ArticleCount;
    /**
     *  多条图文消息信息，默认第一个 item 为大图
      */
    private List<Article> Articles;

}