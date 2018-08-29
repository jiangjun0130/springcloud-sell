package jj.fly.product.repository;

import jj.fly.product.dataobj.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:12
 * Description:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
