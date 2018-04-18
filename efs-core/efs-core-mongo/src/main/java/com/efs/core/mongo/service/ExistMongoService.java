package com.efs.core.mongo.service;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.efs.core.mongo.model.BaseMongoEntity;

@NoRepositoryBean
public interface ExistMongoService<E extends BaseMongoEntity> extends MongoRepository<E, String> {

}
