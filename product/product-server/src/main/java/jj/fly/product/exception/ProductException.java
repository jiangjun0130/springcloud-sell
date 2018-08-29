package jj.fly.product.exception;

import jj.fly.product.enums.ResultEnum;

/**
 * Author: jiangjun
 * Date: 2018/8/2
 * Time: 下午5:33
 * Description:
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException( ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
    }
}
