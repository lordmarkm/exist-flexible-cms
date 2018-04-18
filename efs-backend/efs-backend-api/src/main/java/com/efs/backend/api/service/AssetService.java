package com.efs.backend.api.service;

import org.springframework.http.ResponseEntity;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetService {

    ResponseEntity<AssetInfo> findOne(String projectCode, String assetCode);
    ResponseEntity<AssetInfo> save(AssetInfo asset);

}
