package com.efs.backend.shared.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

@Component
public class DummyAssetClient implements AssetClient {

    @Override
    public ResponseEntity<AssetInfo> getAsset(String assetCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getRepoCode() {
        // TODO Auto-generated method stub
        return "dummy";
    }

}
