package com.efs.backend.api.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.api.service.AssetService;
import com.efs.backend.repo.backend.shared.dto.AssetInfo;

@RestController
@RequestMapping("/api/asset")
public class AssetResource {

    private static final Logger LOG = LoggerFactory.getLogger(AssetResource.class);

    @Autowired
    private AssetService assetService;

    @GetMapping("/find-one")
    public ResponseEntity<AssetInfo> findOne(@RequestParam String projectCode, @RequestParam String assetCode) {
        LOG.debug("AssetResource::findOne({}, {})", projectCode, assetCode);
        return assetService.findOne(projectCode, assetCode);
    }

    @PostMapping
    public ResponseEntity<AssetInfo> save(@RequestBody AssetInfo asset) {
        LOG.debug("AssetResource::save({})", asset);
        return assetService.save(asset);
    }

}
