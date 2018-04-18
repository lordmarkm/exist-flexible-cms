package com.efs.core.mongo.support;

import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.PreSave;

import com.efs.core.mongo.model.BaseMongoEntity;
import com.mynt.core.util.AuthenticationUtil;

public class BaseMongoEntityListener {

    @PrePersist
    public void prePersist(BaseMongoEntity e) {
        e.setCreatedBy(AuthenticationUtil.getLoggedInUsername());
        e.setUpdatedBy(e.getCreatedBy());
    }

    @PreSave
    public void preSave(BaseMongoEntity e) {
        e.setUpdatedBy(AuthenticationUtil.getLoggedInUsername());
    }

}
