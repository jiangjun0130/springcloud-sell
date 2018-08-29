package jj.fly.product.enums;

import lombok.Getter;

/**
 * Author: jiangjun
 * Date: 2018/8/2
 * Time: 下午5:34
 * Description:
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "商品库存错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
