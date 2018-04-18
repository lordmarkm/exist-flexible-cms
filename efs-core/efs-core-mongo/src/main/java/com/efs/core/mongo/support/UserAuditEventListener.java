package com.efs.core.mongo.support;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import com.efs.core.mongo.model.BaseMongoEntity;
import com.mynt.core.util.AuthenticationUtil;

public class UserAuditEventListener extends AbstractMongoEventListener<BaseMongoEntity> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<BaseMongoEntity> event) {
        BaseMongoEntity e = event.getSource();
        e.setUpdatedBy(AuthenticationUtil.getLoggedInUsername());
        if (null == e.getCreatedBy()) {
            e.setCreatedBy(AuthenticationUtil.getLoggedInUsername());
        }
    }

}
