package jj.fly.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Author: jiangjun
 * Date: 2018/8/6
 * Time: 下午5:54
 * Description:
 */
@Data
@Component
@ConfigurationProperties("girl")
@RefreshScope
public class GirlConfig {


    private String name;

    private Integer age;
}
