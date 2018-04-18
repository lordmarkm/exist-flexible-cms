package com.efs.core.mongo.service.custom;

import com.efs.core.mongo.model.BaseMongoEntity;
import com.mynt.core.dto.BaseMongoInfo;

public interface ExistMongoServiceCustom<E extends BaseMongoEntity, D extends BaseMongoInfo> {

    D saveInfo(D dto);

}
