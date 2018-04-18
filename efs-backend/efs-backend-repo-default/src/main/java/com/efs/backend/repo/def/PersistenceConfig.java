package com.efs.backend.repo.def;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.efs.core.mongo.support.UserAuditEventListener;

@Configuration
@EnableMongoAuditing
public class PersistenceConfig {

    @Bean
    public UserAuditEventListener userAuditEventListener() {
        return new UserAuditEventListener();
    }

}
