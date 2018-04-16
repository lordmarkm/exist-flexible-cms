package com.efs.backend.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.mynt.core.jpa.model.BaseEntity;

@Entity(name = "project")
public class Project extends BaseEntity {

    @Column(name = "proj_code")
    private String projectCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
