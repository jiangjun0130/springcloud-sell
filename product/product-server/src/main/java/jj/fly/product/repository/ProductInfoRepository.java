package jj.fly.product.repository;

import jj.fly.product.dataobj.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:03
 * Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {


    List<ProductInfo> findByProductStatus(String productStatus);


    List<ProductInfo> findByProductIdIn(List<String> productIds);

}
