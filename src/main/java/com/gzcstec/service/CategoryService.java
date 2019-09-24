package com.gzcstec.service;

import com.gzcstec.dataobject.ProductCategory;

import java.util.List;

/**
 * @类目
 * Created by Administrator on 2017/10/13 0013.
 */
public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory category);

}
