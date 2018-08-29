package jj.fly.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午10:28
 * Description:
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private ProductFeignClient productFeignClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        // 1:第一种方式 RestTemplate
        // RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://192.168.2.173:8900/8900/msg", String.class);
        // 2：第二种方法loadBalancerClient、RestTemplate
//        ServiceInstance serviceInstance = loadBalancerClient.choose("product");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
//        String response = restTemplate.getForObject(url, String.class);

        // 3: 第三种方式:利用LoadBalanced，可在restTemplate中使用应用名字
//        String response = restTemplate.getForObject("http://product/msg", String.class);

        // String response = productFeignClient.productMsg();
        // log.info("response={}", response);
        return "";
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        //List<ProductInfo> productInfoList = productFeignClient.listForOrder(Arrays.asList("157875196366160022"));
        return "OK";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        //productFeignClient.decreaseStock(Arrays.asList(new CartDto("157875196366160022", 3)));
        return "OK";
    }
}
