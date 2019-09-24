package com.gzcstec.controller;

import com.gzcstec.dataobject.ProductCategory;
import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.exception.SellException;
import com.gzcstec.form.CategoryForm;
import com.gzcstec.form.ProductForm;
import com.gzcstec.service.CategoryService;
import com.gzcstec.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品类目
 * Created by Administrator on 2017/11/2 0002.
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String ,Object> map) {

        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("categories" , productCategoryList);

        return new ModelAndView("category/list" , map);

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId" , required = false ) Integer categoryId ,
                              Map<String,Object> map) {
        if(!StringUtils.isEmpty(categoryId)) {
            //1.查找商品
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category" , productCategory);
        }

        return new ModelAndView("/category/index" , map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String,Object> map) {

        if(bindingResult.hasErrors()) {
            //1.前台数据验证不通过
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("/common/error" , map);
        }

        ProductCategory category = new ProductCategory();

        if(!StringUtils.isEmpty(categoryForm.getCategoryId())) {
            category = categoryService.findOne(categoryForm.getCategoryId());
            if(null == category) {
                log.error("【卖家新增类目】类目不存在，categoryId={}" , categoryForm.getCategoryId());
                map.put("msg" , ExceptionCodeEnums.CATEGORY_NOT_FOUND.getMsg());
                map.put("url" , "/sell/seller/category/index");
                return new ModelAndView("/common/error" , map);
            }
        }

        BeanUtils.copyProperties(categoryForm , category);

        try {
            categoryService.save(category);
        }catch (SellException e) {
            log.error("【卖家新增类目】发生错误，e={}" , e);
            map.put("msg" , ExceptionCodeEnums.CATEGORY_UPDATE_FAIL.getMsg());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("/common/error" , map);
        }

        map.put("msg" , ExceptionCodeEnums.CATEGORY_UPDATE_SUCCESS.getMsg());
        map.put("url" , "/sell/seller/category/list");

        return new ModelAndView("common/success" , map);
    }

}
