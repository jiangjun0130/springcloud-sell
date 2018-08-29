package jj.fly.product.service;

import jj.fly.product.dataobj.ProductCategory;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:25
 * Description:
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

}
