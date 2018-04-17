package com.efs.backend.repo.client.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.repo.client.DefaultAssetClient;
import com.efs.backend.shared.client.AssetClient;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class DefaultAssetClientImpl implements DefaultAssetClient {

    @Override
    public ResponseEntity<AssetInfo> getAsset(String assetCode) {
        AssetInfo asset = new AssetInfo();
        asset.setAssetCode("lecode");
        return new ResponseEntity<>(asset, OK);
    }

    @Override
    public String getRepoCode() {
        return AssetClient.REPO_CODE_DEFAULT;
    }

}
