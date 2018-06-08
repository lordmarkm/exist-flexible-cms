package com.efs.backend.api.dto;

import com.efs.core.dto.BaseInfo;

public class ProjectInfo extends BaseInfo {

    private String projectCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
