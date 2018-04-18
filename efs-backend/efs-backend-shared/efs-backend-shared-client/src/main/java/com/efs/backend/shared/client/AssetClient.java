package com.efs.backend.shared.client;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetClient {

    String REPO_CODE_DEFAULT = "default";

    ResponseEntity<AssetInfo> getAsset(String assetCode);
    ResponseEntity<AssetInfo> saveAsset(AssetInfo asset);
    ResponseEntity<List<AssetInfo>> findByPageCode(String projectCode, String pageCode);

}
