package jj.fly.user.service;

import jj.fly.user.dataobj.UserInfo;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午3:28
 * Description:
 */
public interface UserService {

    UserInfo findByOpenid(String openid);
}
