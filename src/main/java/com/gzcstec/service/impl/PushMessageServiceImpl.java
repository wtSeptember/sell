package com.gzcstec.service.impl;

import com.gzcstec.config.WechatAccountConfig;
import com.gzcstec.dto.OrderDTO;
import com.gzcstec.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateIds().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> datas = Arrays.asList(
                new WxMpTemplateData("keyword1" , orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2" , "微信点餐"),
                new WxMpTemplateData("keyword3" , "1"),
                new WxMpTemplateData("keyword4" , "￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("keyword5" , orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("remark" , "欢迎下次管理!")
        );

        wxMpTemplateMessage.setData(datas);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【订单状态变化通知】通知失败，e={}" , e);
            e.printStackTrace();
        }
    }
}
