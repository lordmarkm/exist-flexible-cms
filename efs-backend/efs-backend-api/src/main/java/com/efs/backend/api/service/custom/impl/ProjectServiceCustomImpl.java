package com.efs.backend.api.service.custom.impl;

import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.model.Project;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.efs.backend.shared.client.AssetClient;
import com.efs.backend.shared.client.DefaultAssetClient;
import com.google.common.collect.Maps;
import com.mynt.core.jpa.service.ExistJpaServiceCustomImpl;
import com.mynt.core.web.exceptions.ExistNotFoundException;

public class ProjectServiceCustomImpl extends ExistJpaServiceCustomImpl<Project, ProjectInfo, ProjectService>
    implements ProjectServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceCustomImpl.class);

    @Autowired
    private DefaultAssetClient defaultAssetClient;

    private Map<String, AssetClient> assetClients = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {
        LOG.warn("Something is masquerading as a defualt asset client! class={}", defaultAssetClient);
        assetClients.put(AssetClient.REPO_CODE_DEFAULT, defaultAssetClient);
    }

    @Override
    public AssetClient getAssetClient(String projectCode) {
        return Optional.ofNullable(assetClients.get(projectCode))
                .orElseThrow(() -> new ExistNotFoundException("No asset client found for " + projectCode));
    }


}
