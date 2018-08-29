package jj.fly.order.service;

import jj.fly.order.dto.OrderDto;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:35
 * Description:
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    OrderDto create(OrderDto orderDto);

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDto finish(String orderId);
}
