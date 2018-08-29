package jj.fly.product.enums;

import lombok.Getter;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:20
 * Description:
 */
@Getter
public enum ProductStatusEnum {

    UP("0", "在架"),
    DOWN("1", "下架"),
    ;

    private String code;

    private String message;

    ProductStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
