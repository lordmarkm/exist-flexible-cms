package com.efs.backend.api.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.service.ProjectService;
import com.mynt.core.web.BaseResource;

@RestController
@RequestMapping("/api/project")
public class ProjectResource extends BaseResource<ProjectInfo, ProjectService> {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectResource.class);

}
