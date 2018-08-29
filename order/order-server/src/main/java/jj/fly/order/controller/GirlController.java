package jj.fly.order.controller;

import jj.fly.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: jiangjun
 * Date: 2018/8/6
 * Time: 下午5:55
 * Description:
 */
@RestController
@RequestMapping("/girl")
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/print")
    public String print(){
        return "name:" + girlConfig.getName() + ";age:" + girlConfig.getAge();
    }
}
