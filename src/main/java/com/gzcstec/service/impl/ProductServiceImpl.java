package com.gzcstec.service.impl;

import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.dataobject.mapper.SellerProductMapper;
import com.gzcstec.dto.CartDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.enums.ProductStatusEnums;
import com.gzcstec.exception.SellException;
import com.gzcstec.repository.ProductInfoRepository;
import com.gzcstec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Service
@CacheConfig(cacheNames = "productInfo")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private SellerProductMapper sellerProductMapper;

    @Override
    @Cacheable(key = "123")
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findByPage(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        //使用mybatis
//        sellerProductMapper.insertByObject(productInfo);
//        sellerProductMapper.updateByObject(productInfo);
//        return new ProductInfo();
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for(CartDTO cartDTO : cartDTOList) {
            //查找商品
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(null == productInfo) {
                throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
            }

            Integer stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if( stock < 0) {
                throw  new SellException(ExceptionCodeEnums.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(stock);
            productInfoRepository.save(productInfo);

        }

    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for(CartDTO cartDTO : cartDTOList) {
            //查找商品
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if(null == productInfo) {
                throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
            }

            Integer stock = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(stock);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo offSafe(ProductInfo productInfo) {
        //1.判断商品状态
        if(ProductStatusEnums.DOWN.getCode()==productInfo.getProductStatus()) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_OFF_SALE_FAIL);
        }
        productInfo.setProductStatus(ProductStatusEnums.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo onSafe(ProductInfo productInfo) {
        //1.判断商品状态
        if(ProductStatusEnums.UP.getCode()==productInfo.getProductStatus()) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_ON_SALE_FAIL);
        }
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
        return productInfoRepository.save(productInfo);
    }
}
