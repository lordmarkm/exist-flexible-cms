package com.efs.backend.repo.backend.shared.dto;

import com.mynt.core.dto.BaseMongoInfo;

public class AssetInfo extends BaseMongoInfo {

    private String projectCode;
    private String assetCode;

    public String getProjectCode() {
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    public String getAssetCode() {
        return assetCode;
    }
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

}
