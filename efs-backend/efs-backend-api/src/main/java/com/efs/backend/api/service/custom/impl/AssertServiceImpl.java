package com.efs.backend.api.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efs.backend.api.service.AssetService;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.shared.client.AssetClient;
import com.efs.backend.shared.dto.AssetInfo;

@Service
public class AssertServiceImpl implements AssetService {

    @Autowired
    private ProjectService projectService;

    @Override
    public AssetInfo findOne(String projectCode, String assetCode) {

        AssetClient client = projectService.getAssetClient(projectCode);
        // TODO Auto-generated method stub
        return null;
    }

}
