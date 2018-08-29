package jj.fly.order.message;

import jj.fly.order.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Author: jiangjun
 * Date: 2018/8/7
 * Time: 上午10:15
 * Description:
 */
@Component
@EnableBinding(value = {StreamClient.class})
@Slf4j
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message){
//        log.info("StreamReceiver: {}", message);
//    }

    @StreamListener(StreamClient.INPUT)
    // 接收到消息后回应
    @SendTo(StreamClient.INPUT2)
    public String process2(OrderDto message){
        log.info("StreamReceiver: {}", message);
        return "received.";
    }

    @StreamListener(StreamClient.INPUT2)
    public void processCallback(Object message){
        log.info("StreamReceiver processCallback: {}", message);

    }
}
