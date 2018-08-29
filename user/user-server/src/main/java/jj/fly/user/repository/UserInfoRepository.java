package jj.fly.user.repository;

import jj.fly.user.dataobj.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午3:27
 * Description:
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
