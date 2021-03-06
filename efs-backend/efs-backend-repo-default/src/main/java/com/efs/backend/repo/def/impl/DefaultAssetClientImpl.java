package com.efs.backend.repo.def.impl;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.def.model.Asset;
import com.efs.backend.repo.def.service.AssetService;
import com.efs.backend.shared.client.DefaultAssetClient;

@RestController
public class DefaultAssetClientImpl implements DefaultAssetClient {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAssetClientImpl.class);

    @Autowired
    private AssetService assetService;

    @Autowired
    private Mapper mapper;

    @Override
    public ResponseEntity<AssetInfo> saveAsset(@RequestBody AssetInfo asset) {
        LOG.debug("DefaultAssetClientImpl::saveAsset({})", asset);
        return new ResponseEntity<>(assetService.saveInfo(asset), CREATED);
    }

    @Override
    public ResponseEntity<AssetInfo> getAsset(String assetCode) {
        LOG.debug("DefaultAssetClientImpl::getAsset({})", assetCode);
        Optional<Asset> asset = assetService.findByAssetCode(assetCode);
        if (asset.isPresent()) {
            return new ResponseEntity<>(mapper.map(asset.get(), AssetInfo.class), OK);
        } else {
            return new ResponseEntity<>(null, NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<AssetInfo>> findByPageCode(String projectCode, String pageCode) {
        List<Asset> assets = assetService.findByProjectCodeAndPageCode(projectCode, pageCode);
        List<AssetInfo> dtos = assets.stream().map(a -> mapper.map(a, AssetInfo.class)).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, OK);
    }

}
