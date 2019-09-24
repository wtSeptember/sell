package com.gzcstec.repository;

import com.gzcstec.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fan on 2017/10/12.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

}
