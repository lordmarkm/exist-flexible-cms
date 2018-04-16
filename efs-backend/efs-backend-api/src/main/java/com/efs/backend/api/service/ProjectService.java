package com.efs.backend.api.service;

import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.efs.backend.api.model.Project;
import com.mynt.core.jpa.service.ExistJpaService;

public interface ProjectService extends ExistJpaService<Project>, ProjectServiceCustom {

}
