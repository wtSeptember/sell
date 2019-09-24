package com.gzcstec.handler;

import com.gzcstec.config.ProjectUrlConfig;
import com.gzcstec.exception.SellException;
import com.gzcstec.exception.SellerAuthorizeException;
import com.gzcstec.utils.ResultUtils;
import com.gzcstec.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录校验异常统一处理类
 * Created by Administrator on 2017/11/6 0006.
 */
@ControllerAdvice
public class AuthorizeExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorize() {
        //1.跳转到登录页面
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getOpenAuthorizeUrl())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=").concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultUtils.error(e.getCode() , e.getMessage());
    }

}
