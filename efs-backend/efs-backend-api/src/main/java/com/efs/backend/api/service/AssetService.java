package com.efs.backend.api.service;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public interface AssetService {

    AssetInfo findOne(String projectCode, String assetCode);

}
