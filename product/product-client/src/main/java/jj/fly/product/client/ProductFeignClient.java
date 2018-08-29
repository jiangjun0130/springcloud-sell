package jj.fly.product.client;

import jj.fly.product.common.DecreaseStockInput;
import jj.fly.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午11:19
 * Description:
 */
@FeignClient(name="product", fallback = ProductFeignClient.ProductFeignClientFallback.class)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/msg")
    String productMsg();

    @GetMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIds);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    static class ProductFeignClientFallback implements ProductFeignClient {

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
}
