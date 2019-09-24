package com.gzcstec.dataobject.mapper;

import com.gzcstec.dataobject.ProductCategory;
import org.apache.ibatis.annotations.Insert;

/**
 * mybatis注入类
 * Created by Administrator on 2017/11/7 0007.
 */

public interface ProductCategoryMapper {

    @Insert("insert into product_category (category_type , category_name) values (#{categoryType , jdbcType=INTEGER} , #{categoryName , jdbcType=VARCHAR})")
    int insertByObject(ProductCategory productCategory);

}
