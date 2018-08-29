package jj.fly.order.repository;

import jj.fly.order.dataobj.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:19
 * Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {


    List<OrderDetail> findByOrderId(String orderId);
}
