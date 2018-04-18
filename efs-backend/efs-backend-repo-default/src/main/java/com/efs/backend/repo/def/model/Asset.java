package com.efs.backend.repo.def.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.efs.core.mongo.model.BaseMongoEntity;

@Document(collection = "asset")
public class Asset extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @Indexed
    private String pageCode;

    @Indexed(unique = true)
    private String assetCode;

    @Indexed
    private String projectCode;

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
    public String getProjectCode() {
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
