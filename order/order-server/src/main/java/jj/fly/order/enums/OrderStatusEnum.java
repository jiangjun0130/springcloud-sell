package jj.fly.order.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:24
 * Description:
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完成"),
    CANCEL(2, "取消"),
    ;

    private Integer status;

    private String message;

    OrderStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
