package jj.fly.product.controller;

import jj.fly.product.dto.CartDto;
import jj.fly.product.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jj.fly.product.dataobj.ProductCategory;
import jj.fly.product.dataobj.ProductInfo;
import jj.fly.product.service.CategoryService;
import jj.fly.product.service.ProductService;
import jj.fly.product.vo.ProductInfoVo;
import jj.fly.product.vo.ProductVo;
import jj.fly.product.vo.ResultVo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: jiangjun
 * Date: 2018/7/30
 * Time: 下午4:47
 * Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo<ProductVo> list(HttpServletRequest request){
        // 查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 构造数据
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory : categoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);

            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }

    /**
     * 根据商品id获取商品列表
     * @param productIds
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIds){
        return productService.findList(productIds);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDto> cartDtoList){
        productService.decreaseStock(cartDtoList);
    }
}
