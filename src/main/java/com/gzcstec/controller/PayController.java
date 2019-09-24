package com.gzcstec.controller;

import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.exception.SellException;
import com.gzcstec.service.OrderService;
import com.gzcstec.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 微信支付
 * Created by Administrator on 2017/10/19 0019.
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId
            , @RequestParam("returnUrl") String returnUrl , Map<String , Object> map) {
        //1查找订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null) {
            log.error("【微信下单】订单不存在，orderId={}" , orderId);
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }

        //2发起支付
        PayResponse payResponse = payService.create(orderDTO);

        //3.存储预支付信息
        map.put("payResponse" , payResponse);
        map.put("returnUrl" , returnUrl);

        //3.唤起支付
        return new ModelAndView("pay/create" , map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
