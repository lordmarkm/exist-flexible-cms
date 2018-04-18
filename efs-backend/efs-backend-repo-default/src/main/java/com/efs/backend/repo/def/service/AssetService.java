package com.efs.backend.repo.def.service;

import java.util.List;
import java.util.Optional;

import com.efs.backend.repo.def.model.Asset;
import com.efs.backend.repo.def.service.custom.AssetServiceCustom;
import com.efs.core.mongo.service.ExistMongoService;

public interface AssetService extends ExistMongoService<Asset>, AssetServiceCustom {

    Optional<Asset> findByAssetCode(String assetCode);
    List<Asset> findByProjectCodeAndPageCode(String projectCode, String pageCode);

}
