package jj.fly.order.dto;

import lombok.Data;

/**
 * Author: jiangjun
 * Date: 2018/8/2
 * Time: 下午5:29
 * Description:
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
