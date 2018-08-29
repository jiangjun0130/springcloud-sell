package jj.fly.order.service.impl;

import jj.fly.order.dataobj.OrderDetail;
import jj.fly.order.dataobj.OrderMaster;
import jj.fly.order.dto.OrderDto;
import jj.fly.order.enums.OrderStatusEnum;
import jj.fly.order.enums.PayStatusEnum;
import jj.fly.order.enums.ResultEnum;
import jj.fly.order.exception.OrderException;
import jj.fly.order.repository.OrderDetailRepository;
import jj.fly.order.repository.OrderMasterRepository;
import jj.fly.order.service.OrderService;
import jj.fly.order.utils.KeyUtil;
import jj.fly.product.client.ProductFeignClient;
import jj.fly.product.common.DecreaseStockInput;
import jj.fly.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:39
 * Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();

        // TODO: 如果是高并发场景：
        // 1：这里的查询商品信息需要到Redis中查询
        // 2：减库存并将新值重新设置到Redis
        // 3：高并发场景还需控制多线程，进行分布式锁的管理
        // 4：如果数据库订单入口异常，需要手动回滚redis

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productFeignClient.listForOrder(productIdList);



        // 计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDto.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 扣库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productFeignClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatus());
        orderMasterRepository.save(orderMaster);
        return orderDto;
    }

    @Transactional
    @Override
    public OrderDto finish(String orderId) {
        // 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if(!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if(OrderStatusEnum.NEW.getStatus() != orderMaster.getOrderStatus()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 设置订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());
        orderMasterRepository.save(orderMaster);
        // 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
