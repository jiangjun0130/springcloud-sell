package jj.fly.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置 Cross Origin Resouce Config
 *
 * Author: jiangjun
 * Date: 2018/8/15
 * Time: 下午5:06
 * Description:
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // cookie跨域
        config.setAllowedOrigins(Arrays.asList("*")); // 原始域
        config.setAllowedHeaders(Arrays.asList("*")); // 原始头
        config.setAllowedMethods(Arrays.asList("*"));   // 允许的方法
        config.setMaxAge(300L); // 缓存时间。对于相同的跨域请求，就不会检查

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
