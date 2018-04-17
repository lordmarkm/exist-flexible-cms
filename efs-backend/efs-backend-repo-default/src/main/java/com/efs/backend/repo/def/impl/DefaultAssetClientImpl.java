package com.efs.backend.repo.def.impl;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.def.client.DefaultAssetClient;

@RestController
public class DefaultAssetClientImpl implements DefaultAssetClient {

    @Override
    public ResponseEntity<AssetInfo> getAsset(String assetCode) {
        AssetInfo asset = new AssetInfo();
        asset.setAssetCode("lecode");
        return new ResponseEntity<>(asset, OK);
    }

}
