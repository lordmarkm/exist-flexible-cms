package com.efs.backend.api.service;

import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;

import com.efs.backend.api.dto.AssetPageInfo;
import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetService {

    ResponseEntity<AssetInfo> findOne(String projectCode, String assetCode);
    ResponseEntity<AssetInfo> save(AssetInfo asset);
    ResponseEntity<AssetPageInfo> findByPage(String projectCode, String pageCode, DateTime updatedDate);

}
