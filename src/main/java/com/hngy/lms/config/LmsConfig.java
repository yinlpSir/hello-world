package com.hngy.lms.config;

import com.hngy.lms.converter.StringToRoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@EnableWebMvc
public class LmsConfig implements WebMvcConfigurer {
    @Autowired
    private StringToRoleConverter stringToRoleConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addConverter(stringToRoleConverter);
    }

    /**
     * 配置 静态资源文件匹配的 url
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // /js/** --> classpath:/static/js/
        registry.addResourceHandler("/js/**", "/css/**", "/img/**")
                .addResourceLocations("classpath:/static/js/",
                        "classpath:/static/css/", "classpath:/static/img/",
                        "classpath:/public/images/");
    }
}
