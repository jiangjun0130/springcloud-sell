package jj.fly.order.controller;

import jj.fly.order.dto.OrderDto;
import jj.fly.order.message.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Author: jiangjun
 * Date: 2018/8/7
 * Time: 上午10:16
 * Description:
 */
@RestController
@Slf4j
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        String message = "now " + new Date();
        try{
            streamClient.input().send(MessageBuilder.withPayload(message).build());
        }catch (Exception e){
            log.error("send msg error:{}", e);
        }

    }

    /**
     * 发送对象
     */
    @GetMapping("/sendMessage2")
    public void process2(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("123456");
        try{
            // streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
            streamClient.input().send(MessageBuilder.withPayload(orderDto).build());
        }catch (Exception e){
            log.error("send msg error:{}", e);
        }

    }

}
