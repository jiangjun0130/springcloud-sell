package jj.fly.product.service.impl;

import jj.fly.product.common.ProductInfoOutput;
import jj.fly.product.dataobj.ProductInfo;
import jj.fly.product.dto.CartDto;
import jj.fly.product.enums.ProductStatusEnum;
import jj.fly.product.enums.ResultEnum;
import jj.fly.product.exception.ProductException;
import jj.fly.product.repository.ProductInfoRepository;
import jj.fly.product.service.ProductService;
import jj.fly.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:18
 * Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDtoList);

        // 发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDto> cartDtoList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for(CartDto cartDto : cartDtoList){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
            // 判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            // 库存是否足够
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
