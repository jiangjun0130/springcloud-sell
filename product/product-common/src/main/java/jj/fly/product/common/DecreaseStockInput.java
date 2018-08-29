package jj.fly.product.common;

import lombok.Data;

/**
 * Author: jiangjun
 * Date: 2018/8/4
 * Time: 下午3:56
 * Description:
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}