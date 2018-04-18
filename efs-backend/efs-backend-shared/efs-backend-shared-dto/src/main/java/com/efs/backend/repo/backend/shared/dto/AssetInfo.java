package com.efs.backend.repo.backend.shared.dto;

import org.springframework.core.style.ToStringCreator;

import com.mynt.core.dto.BaseMongoInfo;

public class AssetInfo extends BaseMongoInfo {

    private String projectCode;
    private String pageCode;
    private String assetCode;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("project", projectCode)
                .append("page", pageCode)
                .append("asset", assetCode);
    }

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
    public String getPageCode() {
        return pageCode;
    }
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

}
