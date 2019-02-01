package com.alx.weixin.wxsell.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alx.weixin.wxsell.entity.OrderMaster;
import com.alx.weixin.wxsell.service.OrderMasterService;

/**
 * 订单主表控制器
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@RestController
@RequestMapping("/orderMaster")
public class OrderMasterController {
	
}