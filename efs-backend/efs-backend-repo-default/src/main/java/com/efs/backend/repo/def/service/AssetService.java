package com.efs.backend.repo.def.service;

import com.efs.backend.repo.def.model.Asset;
import com.efs.backend.repo.def.service.custom.AssetServiceCustom;
import com.efs.core.mongo.service.ExistMongoService;

public interface AssetService extends ExistMongoService<Asset>, AssetServiceCustom {

}
