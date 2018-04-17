package com.efs.backend.api.service.custom.impl;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efs.backend.api.service.AssetService;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.shared.client.AssetClient;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private ProjectService projectService;

    @Override
    public AssetInfo findOne(String projectCode, String assetCode) {
        AssetClient client = projectService.getAssetClient(projectCode);
        return client.getAsset(assetCode).getBody();
    }

}
