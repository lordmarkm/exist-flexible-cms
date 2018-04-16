package com.efs.backend.api.service.custom.impl;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.model.Project;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.efs.backend.shared.client.AssetClient;
import com.google.common.collect.Maps;
import com.mynt.core.jpa.service.ExistJpaServiceCustomImpl;
import com.mynt.core.web.exceptions.ExistNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectServiceCustomImpl extends ExistJpaServiceCustomImpl<Project, ProjectInfo, ProjectService>
    implements ProjectServiceCustom {

    private Map<String, AssetClient> assetClients;

    @Autowired
    public ProjectServiceCustomImpl(List<AssetClient> assetClientList) {
        this.assetClients = Maps.newHashMap();
        assetClientList.forEach(client -> {
            assetClients.put(client.getRepoCode(), client);
        });

        //TODO programatically create dynamic clients here
    }

    @Override
    public AssetClient getAssetClient(String projectCode) {
        return Optional.ofNullable(assetClients.get(projectCode))
                .orElseThrow(() -> new ExistNotFoundException("No asset client found for " + projectCode));
    }


}
