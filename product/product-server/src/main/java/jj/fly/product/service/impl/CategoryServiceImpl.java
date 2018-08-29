package jj.fly.product.service.impl;

import jj.fly.product.dataobj.ProductCategory;
import jj.fly.product.repository.ProductCategoryRepository;
import jj.fly.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:28
 * Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return categoryRepository.findByCategoryTypeIn(types);
    }
}
