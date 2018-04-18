package com.efs.backend.repo.def.model;

import com.efs.core.mongo.model.BaseMongoEntity;

public class Asset extends BaseMongoEntity {

    private String pageCode;
    private String assetCode;

    public String getPageCode() {
        return pageCode;
    }
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }
    public String getAssetCode() {
        return assetCode;
    }
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

}
