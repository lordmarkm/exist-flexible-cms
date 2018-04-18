package com.efs.backend.repo.def.service.custom;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.def.model.Asset;
import com.efs.core.mongo.service.custom.ExistMongoServiceCustom;

public interface AssetServiceCustom extends ExistMongoServiceCustom<Asset, AssetInfo> {

}
