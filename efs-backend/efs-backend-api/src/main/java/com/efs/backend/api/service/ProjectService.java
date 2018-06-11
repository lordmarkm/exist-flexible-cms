package com.efs.backend.api.service;

import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.efs.core.jpa.service.ExistJpaService;
import com.efs.backend.api.model.Project;

public interface ProjectService extends ExistJpaService<Project>, ProjectServiceCustom {

}
