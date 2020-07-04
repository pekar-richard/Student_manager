package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MyWebMvcConfig implements WebMvcConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "OPTIONS","POST","DELETE")
                    .allowedHeaders("Origin", "X-Requested-With", "Content-Type","Accept","Authorization")
                    .exposedHeaders("Origin", "X-Requested-With", "Content-Type","Accept","Authorization")
                    .allowCredentials(true).maxAge(3600);
   }
 }