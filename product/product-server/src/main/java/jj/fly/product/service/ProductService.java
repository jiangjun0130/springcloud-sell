package jj.fly.product.service;

import jj.fly.product.dataobj.ProductInfo;
import jj.fly.product.dto.CartDto;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:17
 * Description:
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 减库存
     * @param cartDtoList
     */
    void decreaseStock(List<CartDto> cartDtoList);
}
