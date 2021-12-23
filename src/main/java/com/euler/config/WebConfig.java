package com.euler.config;

import com.euler.interceptor.CorsFilter;
import com.euler.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/user/login", "/error","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    /**
     * 注册跨域拦截器bean组件
     */
    @Bean
    public FilterRegistrationBean corsFilter(){
        return new FilterRegistrationBean(new CorsFilter());
    }

//    /**
//     * 解决谷歌浏览器SameSite属性默认Lax,引发跨域问题，下面的设置与corsFilter里的setHeaders("Set-Cookie")方法一样
//     */
//    @Bean
//    public CookieSerializer httpSessionIdResolver() {
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setUseHttpOnlyCookie(false);
//        cookieSerializer.setSameSite("None");
//        //cookieSerializer.setCookiePath("/");
//        cookieSerializer.setUseSecureCookie(true);
//        return cookieSerializer;
//    }


    /**
     * 允许通过swagger相关资源请求
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }
}
