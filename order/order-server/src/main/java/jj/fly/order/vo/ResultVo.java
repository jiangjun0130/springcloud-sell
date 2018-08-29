package jj.fly.order.vo;

import lombok.Data;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:32
 * Description:
 */
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    private String msg;

    private T data;
}
