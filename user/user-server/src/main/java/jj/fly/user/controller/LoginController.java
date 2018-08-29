package jj.fly.user.controller;

import jj.fly.user.constant.CookieConstant;
import jj.fly.user.constant.RedisConstant;
import jj.fly.user.dataobj.UserInfo;
import jj.fly.user.enums.ResultEnum;
import jj.fly.user.enums.RoleEnum;
import jj.fly.user.service.UserService;
import jj.fly.user.utils.CookieUtil;
import jj.fly.user.utils.ResultVoUtil;
import jj.fly.user.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午3:29
 * Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        // 1:
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2:判断角色
        if(RoleEnum.BUYER.getCode() != userInfo.getRole()){
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 3:设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVoUtil.success();
    }

    @GetMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid, HttpServletResponse response,
                           HttpServletRequest request){

        // 判断是否已登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null && StringUtils.isNotEmpty(redisTemplate.opsForValue().get(
                String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
            return ResultVoUtil.success();
        }
        // 1:
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2:判断角色
        if(RoleEnum.SELLER.getCode() != userInfo.getRole()){
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3:redis设置key=UUID， value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        String key = String.format(RedisConstant.TOKEN_TEMPLATE, token);
        redisTemplate.opsForValue().set(key,
                openid,
                expire,
                TimeUnit.SECONDS);

        // 4:设置cookie openid=xyz
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVoUtil.success();
    }

}
