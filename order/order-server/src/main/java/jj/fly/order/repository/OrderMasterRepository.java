package jj.fly.order.repository;

import jj.fly.order.dataobj.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午9:18
 * Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
