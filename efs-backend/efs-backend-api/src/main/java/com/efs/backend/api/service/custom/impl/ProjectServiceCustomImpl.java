package com.efs.backend.api.service.custom.impl;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.model.Project;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.mynt.core.jpa.service.ExistJpaServiceCustomImpl;

public class ProjectServiceCustomImpl extends ExistJpaServiceCustomImpl<Project, ProjectInfo, ProjectService>
    implements ProjectServiceCustom {

}
