package com.alx.weixin.common.until;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    public static <T, E> T copyProperties(Class<T> clz, E from) {
        String ob = JSONObject.toJSONString(from);
        return JSONObject.parseObject(ob, clz);
    }

    public static <T, E> List<T> copyProperties(Class<T> clz, List<E> from) {
        List<T> list = new ArrayList<T>();
        if (from == null) {
            return null;
        }
        for (E model : from) {
            T t = copyProperties(clz, model);
            list.add(t);
        }
        return list;
    }

    public static <T, E> Page<T> copyProperties(Class<T> clz, Page<E> from) {
        if (from == null) {
            return null;
        }
        Page<T> page = new Page<T>(from.getCurrent(), from.getSize(), from.getOrderByField());
        page.setRecords(copyProperties(clz, from.getRecords()));
        page.setTotal(from.getTotal());
        return page;
    }

}
