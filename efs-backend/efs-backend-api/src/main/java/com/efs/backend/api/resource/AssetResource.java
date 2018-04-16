package com.efs.backend.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efs.backend.api.service.AssetService;
import com.efs.backend.shared.dto.AssetInfo;

@RestController("/asset")
public class AssetResource {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<AssetInfo> findOne(@RequestParam String projectCode, @RequestParam String assetCode) {
        return assetService.findOne(projectCode, assetCode);
    }

}
