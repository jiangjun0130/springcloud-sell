package jj.fly.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午10:26
 * Description:
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is jj.fly.product msg!";
    }
}
