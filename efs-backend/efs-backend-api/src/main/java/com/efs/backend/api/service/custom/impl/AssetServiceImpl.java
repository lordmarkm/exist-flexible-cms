package com.efs.backend.api.service.custom.impl;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.efs.backend.api.service.AssetService;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.shared.client.AssetClient;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<AssetInfo> findOne(String projectCode, String assetCode) {
        AssetClient client = projectService.getAssetClient(projectCode);
        return client.getAsset(assetCode);
    }

}
