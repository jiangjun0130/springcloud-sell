package jj.fly.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Author: jiangjun
 * Date: 2018/8/7
 * Time: 上午10:14
 * Description:
 */
public interface StreamClient {

    String INPUT = "myMessageInput";
    String OUTPUT = "myMessageOutput";

    String INPUT2 = "myMessageInput2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT2)
    SubscribableChannel input2();
}
