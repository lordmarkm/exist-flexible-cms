package com.efs.backend.api.service.custom;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.model.Project;
import com.efs.backend.shared.client.AssetClient;
import com.mynt.core.jpa.service.ExistJpaServiceCustom;


public interface ProjectServiceCustom extends ExistJpaServiceCustom<Project, ProjectInfo> {

    AssetClient getAssetClient(String projectCode);

}
