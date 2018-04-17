package com.efs.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.efs.backend.repo.def.client.DefaultAssetClient;

@SpringBootApplication(scanBasePackages = {
    "com.efs.core",
    "com.efs.backend.api",
    "com.efs.backend.repo.def.client"
})
@EnableFeignClients(clients = {
    DefaultAssetClient.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource utibaMessageSource = new ResourceBundleMessageSource();
        utibaMessageSource.setBasename("messages");
        utibaMessageSource.setDefaultEncoding("UTF-8");
        utibaMessageSource.setCacheSeconds(60);
        return utibaMessageSource;
    }

}
