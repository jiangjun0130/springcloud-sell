package jj.fly.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author: jiangjun
 * Date: 2018/8/6
 * Time: 下午10:29
 * Description:
 */
@Slf4j
@Component
public class MqReceiver {

    //1. @RabbitListener(queues = "myQueue")
    //2. 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3. 自动创建队列,Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(value=@Queue("myQueue"),exchange=@Exchange("myExchage")))
    public void process(String message){
        log.info("MqReceiver:{}", message);
    }


    /**
     * 数码供应商服务 接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange("myOrder"),
            key="computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message){
        log.info("computer MqReceiver:{}", message);
    }

    /**
     * 水果供应商服务 接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange("myOrder"),
            key="fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message){
        log.info("fruit MqReceiver:{}", message);
    }
}
