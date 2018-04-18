package com.efs.backend.api.dto;

import java.util.Map;

import org.joda.time.DateTime;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

public class AssetPageInfo {

    private String pageCode;
    private DateTime updatedDate;
    private Map<String, AssetInfo> assets;

    public Map<String, AssetInfo> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, AssetInfo> assets) {
        this.assets = assets;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

}
