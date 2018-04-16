package com.efs.backend.api.resource;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.api.service.AssetService;

@RestController("/asset")
public class AssetResource {

    private static final Logger LOG = LoggerFactory.getLogger(AssetResource.class);

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<AssetInfo> findOne(@RequestParam String projectCode, @RequestParam String assetCode) {
        return new ResponseEntity<>(assetService.findOne(projectCode, assetCode), HttpStatus.OK);
    }

}
