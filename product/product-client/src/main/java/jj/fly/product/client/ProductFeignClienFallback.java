package jj.fly.product.client;

import jj.fly.product.common.DecreaseStockInput;
import jj.fly.product.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/8/20
 * Time: 下午5:06
 * Description:
 */
@Component
public class ProductFeignClienFallback implements ProductFeignClient {

    @Override
    public String productMsg() {
        return null;
    }

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIds) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
