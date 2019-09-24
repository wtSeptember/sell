package com.gzcstec.controller;

import com.gzcstec.dataobject.ProductCategory;
import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.service.CategoryService;
import com.gzcstec.service.ProductService;
import com.gzcstec.utils.ResultUtils;
import com.gzcstec.vo.ProductInfoVO;
import com.gzcstec.vo.ProductVO;
import com.gzcstec.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买点端商品
 * Created by Administrator on 2017/10/13 0013.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product" , key = "#sellerId" , condition = "#sellerId.length() > 3")
    public ResultVO<List<ProductVO>> list(@RequestParam("sellerId") String sellerId) {
        //获取所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //获取所有的类目
        List<Integer> categoryTypes = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);

        //拼接VO
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList) {
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo , productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfos(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultUtils.success(productVOList);
    }

}
