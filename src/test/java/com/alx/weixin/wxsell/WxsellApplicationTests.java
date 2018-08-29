package com.alx.weixin.wxsell;

import com.alx.weixin.wechat.common.WeChatTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxsellApplicationTests {

	@Test
	public void contextLoads() {

		WeChatTask w = new WeChatTask();

		try {
			w.getToken_getTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
