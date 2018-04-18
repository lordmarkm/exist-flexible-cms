package com.efs.backend.repo.def.service.custom.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.def.model.Asset;
import com.efs.backend.repo.def.service.AssetService;
import com.efs.backend.repo.def.service.custom.AssetServiceCustom;

public class AssetServiceCustomImpl implements AssetServiceCustom {

    @Autowired
    private AssetService repo;

    @Autowired
    private Mapper mapper;

    @Override
    public AssetInfo saveInfo(AssetInfo dto) {
        Asset asset = mapper.map(dto, Asset.class);
        return mapper.map(repo.save(asset), AssetInfo.class);
    }

}
