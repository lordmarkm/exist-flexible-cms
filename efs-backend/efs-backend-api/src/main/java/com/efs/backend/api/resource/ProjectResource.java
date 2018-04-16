package com.efs.backend.api.resource;

import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.service.ProjectService;
import com.mynt.core.web.BaseResource;

@RestController("/project")
public class ProjectResource extends BaseResource<ProjectInfo, ProjectService> {

}
