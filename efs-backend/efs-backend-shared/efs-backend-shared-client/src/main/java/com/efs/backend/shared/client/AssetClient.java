package com.efs.backend.shared.client;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetClient {

    String getRepoCode();
    AssetInfo getAsset(String assetCode);

}
