package jj.fly.product.utils;

import jj.fly.product.vo.ResultVo;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午5:13
 * Description:
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("获取成功！");
        return resultVo;
    }

}
