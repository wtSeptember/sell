package com.gzcstec.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gzcstec.dataobject.OrderDetail;
import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.exception.SellException;
import com.gzcstec.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家订单表单转换orderDTO
 * Created by Administrator on 2017/10/18 0018.
 */
@Slf4j
public class ConvertOrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        //订单详情
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems() ,
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e) {
            log.error("【对象装换】错误，string={}" , orderForm.getItems());
            throw new SellException(ExceptionCodeEnums.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
