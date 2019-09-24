package com.gzcstec.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端的数据封装
 * Created by Administrator on 2017/10/13 0013.
 */
@Data
public class ResultVO<T> implements Serializable{
    private static final long serialVersionUID = -4414926978700453869L;
    /** 状态码 0 表示成功 */
    private Integer code;

    /** 状态码说明 */
    private String msg;

    /** 返回数据*/
    private T data;

}
