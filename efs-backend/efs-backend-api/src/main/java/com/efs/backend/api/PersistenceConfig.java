package com.efs.backend.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
    "com.efs.backend.api.service"
}, repositoryImplementationPostfix = "CustomImpl")
public class PersistenceConfig {

}