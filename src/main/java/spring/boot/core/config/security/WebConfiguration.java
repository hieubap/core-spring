package spring.boot.core.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
//@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${app.storage.location:./files/public}")
    private String filesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + (filesPath.startsWith("/") ? "//" : "") + filesPath + "/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Map the Swagger UI path
        registry.addViewController("/swagger-ui.html").setViewName("forward:/swagger-ui.html");
        registry.addViewController("/api-docs").setViewName("forward:/api-docs");
        registry.addViewController("/swagger-resources/**").setViewName("forward:/swagger-resources/**");
        registry.addViewController("/webjars/**").setViewName("forward:/webjars/**");
    }
}