package jj.fly.user.utils;

import jj.fly.user.enums.ResultEnum;
import jj.fly.user.vo.ResultVo;

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

    public static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultEnum.LOGIN_SUCCESS.getCode());
        resultVo.setMsg(ResultEnum.LOGIN_SUCCESS.getMessage());
        return resultVo;
    }

    public static ResultVo error(ResultEnum resultEnum){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMessage());
        return resultVo;
    }

}
