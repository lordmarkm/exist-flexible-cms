package com.efs.backend.api.service.custom.impl;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.efs.backend.api.dto.AssetPageInfo;
import com.efs.backend.api.service.AssetService;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.repo.backend.shared.dto.AssetInfo;
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

    @Override
    public ResponseEntity<AssetInfo> save(AssetInfo asset) {
        AssetClient client = projectService.getAssetClient(asset.getProjectCode());
        return client.saveAsset(asset);
    }

    @Override
    public ResponseEntity<AssetPageInfo> findByPage(String projectCode, String pageCode, DateTime updatedDate) {
        AssetClient client = projectService.getAssetClient(projectCode);
        ResponseEntity<List<AssetInfo>> resp = client.findByPageCode(projectCode, pageCode);
        if (!resp.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(null, resp.getStatusCode());
        }

        Map<String, AssetInfo> assetMap = resp.getBody().stream().collect(Collectors.toMap(AssetInfo::getAssetCode, a -> a));

        AssetPageInfo page = new AssetPageInfo();
        page.setPageCode(pageCode);
        page.setUpdatedDate(updatedDate);
        page.setAssets(assetMap);

        return new ResponseEntity<>(page, OK);
    }

}
