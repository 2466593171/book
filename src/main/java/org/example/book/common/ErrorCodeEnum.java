package org.example.book.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 正确执行后的返回
     * */
    OK("00000","一切 ok"),

    /**
     * 一级宏观错误码，用户端错误
     * */
    USER_ERROR("A0001","用户端错误"),

    /**
     * 二级宏观错误码，用户注册错误
     * */
    USER_REGISTER_ERROR("A0100","用户注册错误"),

    /**
     * 二级宏观错误码，用户未同意隐私协议
     * */
    USER_NO_AGREE_PRIVATE_ERROR("A0101","用户未同意隐私协议"),

    /**
     * 二级宏观错误码，注册国家或地区受限
     * */
    USER_REGISTER_AREA_LIMIT_ERROR("A0102","注册国家或地区受限"),

    /**
     * 二级宏观错误码，用户请求参数错误
     * */
    USER_REQUEST_PARAM_ERROR("A0400","用户请求参数错误"),

    // ...省略若干用户端二级宏观错误码

    /**
     * 一级宏观错误码，系统执行出错
     * */
    SYSTEM_ERROR("B0001","系统执行出错"),

    /**
     * 二级宏观错误码，系统执行超时
     * */
    SYSTEM_TIMEOUT_ERROR("B0100","系统执行超时"),

    // ...省略若干系统执行二级宏观错误码

    /**
     * 一级宏观错误码，调用第三方服务出错
     * */
    THIRD_SERVICE_ERROR("C0001","调用第三方服务出错"),

    /**
     * 一级宏观错误码，中间件服务出错
     * */
    MIDDLEWARE_SERVICE_ERROR("C0100","中间件服务出错")

    // ...省略若干三方服务调用二级宏观错误码

    ;

    /**
     * 错误码
     * */
    private String code;

    /**
     * 中文描述
     * */
    private String message;

}