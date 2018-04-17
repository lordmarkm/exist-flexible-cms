package com.efs.backend.shared.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetClient {

    String REPO_CODE_DEFAULT = "default";

    @RequestMapping(value = "/asset")
    ResponseEntity<AssetInfo> getAsset(@RequestParam("assetCode") String assetCode);
    String getRepoCode();

}
