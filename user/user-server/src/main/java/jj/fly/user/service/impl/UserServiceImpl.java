package jj.fly.user.service.impl;

import jj.fly.user.dataobj.UserInfo;
import jj.fly.user.repository.UserInfoRepository;
import jj.fly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午3:28
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
