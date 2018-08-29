package jj.fly.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Author: jiangjun
 * Date: 2018/8/16
 * Time: 下午2:46
 * Description:
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    // TODO:假定该方法是业务核心方法，就可以为该核心方法指定特殊的fallback进行特殊处理，其他非核心方法就可以通过默认fallback进行统一静态页面跳转等处理
    // @HystrixCommand(fallbackMethod = "fallback")

    // 不用fallbackMethod参数指定，就用默认的defaultFallback进行降级处理
    // @HystrixCommand

    // 超时配置
    // @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})

    // 熔断
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),   // 断路器的最小请求数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 休眠时间窗为10s，10s后断路器打开一个请求，如果成功则关闭断路器，都在打开断路器
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60")  // 断路器打开请求错误比例
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number){
        // TODO: 目标服务异常，或者本身服务内部异常，都可以触发降级

        // 测试熔断。偶数返回成功，否则触发降级并且进行熔断处理
        if(number % 2 == 0){
            return "success";
        }
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject("http://127.0.0.1:8900/product/listForOrder", Arrays.asList("157875196366160022"), String.class);

        throw new RuntimeException("发生异常！");
    }

    private String fallback(){
        return "太拥挤了，请稍后再试！";
    }

    private String defaultFallback(){
        return "默认提示：太拥挤了，请稍后再试！";

    }

}
