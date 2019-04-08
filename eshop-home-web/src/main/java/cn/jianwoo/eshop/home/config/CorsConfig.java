package cn.jianwoo.eshop.home.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private static String[] originsVal = new String[]{
            "localhost:8010",
            "localhost:8004",
            "localhost:8005",
            "localhost:8009",
            "localhost:8007",
            "localhost:8003"
    };

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        addAllowedOrigins(corsConfiguration); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
         corsConfiguration.setAllowCredentials(true); // 跨域session共享
        source.registerCorsConfiguration("/**", corsConfiguration); // 4
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
        for (String origin : originsVal) {
            corsConfiguration.addAllowedOrigin("http://" + origin);
            corsConfiguration.addAllowedOrigin("https://" + origin);
        }
    }
}

