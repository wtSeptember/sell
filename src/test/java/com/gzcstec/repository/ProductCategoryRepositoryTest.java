package com.gzcstec.repository;

import com.gzcstec.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fan on 2017/10/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        System.out.println(productCategoryRepository.findOne(1));
    }

    @Test
    public void findAllTest() {
        System.out.println(productCategoryRepository.findAll());
    }

    @Test
    @Transactional
    public void saveTest() {
       ProductCategory productCategory = new ProductCategory();
       productCategory.setCategoryName("小孩最爱");
       productCategory.setCategoryType(4);

       productCategoryRepository.save(productCategory);




    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(3);
        productCategory.setCategoryName("男生最爱");

        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> categoryType = Arrays.asList(2,3,4);

        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryType);
        Assert.assertNotEquals(0 , result.size());
    }
}