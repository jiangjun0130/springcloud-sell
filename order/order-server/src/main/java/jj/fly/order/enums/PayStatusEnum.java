package jj.fly.order.enums;

import lombok.Getter;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:24
 * Description:
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer status;

    private String message;

    PayStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
