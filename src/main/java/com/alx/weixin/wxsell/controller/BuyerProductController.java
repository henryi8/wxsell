package com.alx.weixin.wxsell.controller;

import com.alx.weixin.wxsell.dateobject.ProductCategory;
import com.alx.weixin.wxsell.dateobject.ProductInfo;
import com.alx.weixin.wxsell.service.CategoryService;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.alx.weixin.wxsell.vo.ProductInfoVo;
import com.alx.weixin.wxsell.vo.ProductVo;
import com.alx.weixin.wxsell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @描述 买家商品
 * @创建人 zhaoxny
 * @创建时间 2018/7/31
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2。查询类目（一次性查询）
        //传统方法
//        List<Integer> categoryType = new ArrayList<>();
//        for (ProductInfo productInfo: upAll){
//            categoryType.add(productInfo.getCategoryType());
//        }
        //精简法（java8 ， lambda）
        List<Integer> categoryType = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryType);

        //3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            //遍历类目
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> proInfoList = new ArrayList<>();
            //遍历所有上架商品
            for (ProductInfo productInfo: productInfoList){
                //如果是该类目下的商品那就添加进去
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    proInfoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(proInfoList);
            productVoList.add(productVo);
        }
        return ResultVo.success(productVoList);
    }

}
