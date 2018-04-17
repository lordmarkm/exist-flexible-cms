package com.efs.backend.api.service.custom.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.efs.backend.api.dto.ProjectInfo;
import com.efs.backend.api.model.Project;
import com.efs.backend.api.service.ProjectService;
import com.efs.backend.api.service.custom.ProjectServiceCustom;
import com.efs.backend.shared.client.AssetClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mynt.core.jpa.service.ExistJpaServiceCustomImpl;
import com.mynt.core.web.exceptions.ExistNotFoundException;

public class ProjectServiceCustomImpl extends ExistJpaServiceCustomImpl<Project, ProjectInfo, ProjectService>
    implements ProjectServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceCustomImpl.class);

//    @Autowired
    private List<AssetClient> assetClientList = Lists.newArrayList();

    private Map<String, AssetClient> assetClients;

    @PostConstruct
    public void initMap() {
        LOG.debug("Initializing asset clients");
        this.assetClients = Maps.newHashMap();
        assetClientList.forEach(client -> {
            LOG.debug("Adding client. code = {}", client.getRepoCode());
            assetClients.put(client.getRepoCode(), client);
        });
    }

    //    @Autowired(required = false)
    //    public ProjectServiceCustomImpl(List<AssetClient> assetClientList) {
    //        this.assetClients = Maps.newHashMap();
    //        assetClientList.forEach(client -> {
    //            assetClients.put(client.getRepoCode(), client);
    //        });
    //
    //        //TODO programatically create dynamic clients here
    //    }

    @Override
    public AssetClient getAssetClient(String projectCode) {
        return Optional.ofNullable(assetClients.get(projectCode))
                .orElseThrow(() -> new ExistNotFoundException("No asset client found for " + projectCode));
    }


}
