package com.gzcstec.service.impl;

import com.gzcstec.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory category = categoryService.findOne(1);

        Assert.assertEquals(new Integer(1) , category.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> categories = categoryService.findAll();

        Assert.assertNotEquals(0 , categories.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));

        Assert.assertNotEquals( 0 , categories.size());

    }

    @Test
    @Transactional
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("男生专区");

        productCategory = categoryService.save(productCategory);

        Assert.assertNotEquals(null , productCategory);

    }

}