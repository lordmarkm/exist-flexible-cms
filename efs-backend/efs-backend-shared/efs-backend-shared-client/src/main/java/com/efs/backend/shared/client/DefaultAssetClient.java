package com.efs.backend.shared.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;

@FeignClient(name = AssetClient.REPO_CODE_DEFAULT, fallback = DefaultAssetClient.DefaultAssetClientFallback.class)
public interface DefaultAssetClient extends AssetClient {

    @Override
    @RequestMapping(value = "/asset/find-by-page-code", method = RequestMethod.GET)
    ResponseEntity<List<AssetInfo>> findByPageCode(@RequestParam("projectCode") String projectCode,
            @RequestParam("pageCode") String pageCode);

    @Override
    @RequestMapping(value = "/asset", method = RequestMethod.GET)
    ResponseEntity<AssetInfo> getAsset(@RequestParam("assetCode") String assetCode);

    @Override
    @RequestMapping(value = "/asset", method = RequestMethod.POST)
    ResponseEntity<AssetInfo> saveAsset(@RequestBody AssetInfo asset);

    @Component
    class DefaultAssetClientFallback implements DefaultAssetClient {

        private static final Logger LOG = LoggerFactory.getLogger(DefaultAssetClientFallback.class);

        @Override
        public ResponseEntity<AssetInfo> getAsset(String assetCode) {
            LOG.error("[FALLBACK] Unable to connect to DefaultAssetClient::getAsset");
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }

        @Override
        public ResponseEntity<AssetInfo> saveAsset(AssetInfo asset) {
            LOG.error("[FALLBACK] Unable to connect to DefaultAssetClient::saveAsset");
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }

        @Override
        public ResponseEntity<List<AssetInfo>> findByPageCode(String projectCode, String pageCode) {
            LOG.error("[FALLBACK] Unable to connect to DefaultAssetClient::saveAsset");
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
