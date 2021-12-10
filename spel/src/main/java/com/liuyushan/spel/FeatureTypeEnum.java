package com.liuyushan.spel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeatureTypeEnum{

    /**
     * 人脸
     */
    FA("fa", "人脸"),
    /**
     * 指纹
     */
    FP("fp", "指纹"),
    /**
     * 卡
     */
    CARD("card", "卡"),
    /**
     * 密码
     */
    PASSWORD("pass", "密码"),
    /**
     * 身份证
     */
    IDENTITY("identity", "身份证"),
    /**
     * 授权码
     */
    AUTH_CODE("auth_code", "授权码");

    private final String value;
    private final String title;

}