package com.efs.backend.repo.def.service.custom.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.def.model.Asset;
import com.efs.backend.repo.def.service.AssetService;
import com.efs.backend.repo.def.service.custom.AssetServiceCustom;

public class AssetServiceCustomImpl implements AssetServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(AssetServiceCustomImpl.class);

    @Autowired
    private AssetService repo;

    @Autowired
    private Mapper mapper;

    @Override
    public AssetInfo saveInfo(AssetInfo dto) {
        Asset asset = mapper.map(dto, Asset.class);
        asset = repo.save(asset);
        AssetInfo saved =  mapper.map(asset, AssetInfo.class);
        LOG.debug("Saved asset. asset={}", saved);

        return saved;
    }

}
