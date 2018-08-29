package jj.fly.user.enums;

import lombok.Getter;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午4:04
 * Description:
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "买家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
