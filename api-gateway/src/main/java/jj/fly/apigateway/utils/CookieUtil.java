package jj.fly.apigateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午4:06
 * Description:
 */
public class CookieUtil {

    public static void set(HttpServletResponse response,
                           String key,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(key.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
