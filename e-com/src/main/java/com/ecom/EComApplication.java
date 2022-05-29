package com.ecom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication

@ComponentScan("com.ecom.repository")
@EntityScan("com.ecom.entity")
@EnableJpaRepositories(basePackages = {"com.ecom.repository"})
public class EComApplication {

    @Value("${baseurl.ecom.origin}")
    private String baseOrigin;
    @Value("${localurl.ecom.origin}")
    private String localOrigin;

    public static void main(String[] args) {
        System.out.println("Application Running");
        SpringApplication.run(EComApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*")
                        .allowedOrigins(baseOrigin, localOrigin)
                        .allowCredentials(true);
            }
        };
    }

}
