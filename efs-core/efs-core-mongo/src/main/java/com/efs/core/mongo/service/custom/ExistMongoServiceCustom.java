package com.efs.core.mongo.service.custom;

import com.efs.core.dto.BaseMongoInfo;
import com.efs.core.mongo.model.BaseMongoEntity;

public interface ExistMongoServiceCustom<E extends BaseMongoEntity, D extends BaseMongoInfo> {

    D saveInfo(D dto);

}
