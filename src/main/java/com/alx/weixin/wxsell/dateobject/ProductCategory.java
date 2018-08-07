package com.alx.weixin.wxsell.dateobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @描述 类目
 * @创建人 zhaoxny
 * @创建时间 2018年7月29日 15:34:11
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增类型
    private Integer categoryId;     //类目id

    private String categoryName;    //类目名字
    private Integer categoryType;   //类目编号
    private Date createTime;
    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
