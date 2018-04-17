package com.efs.backend.repo.def.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.efs.backend.repo.backend.shared.dto.AssetInfo;
import com.efs.backend.shared.client.AssetClient;

@FeignClient(name = AssetClient.REPO_CODE_DEFAULT, fallback = DefaultAssetClient.DefaultAssetClientFallback.class)
public interface DefaultAssetClient extends AssetClient {

    @RequestMapping(value = "/asset", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<AssetInfo> getAsset(String assetCode);

    @Component
    class DefaultAssetClientFallback implements DefaultAssetClient {

        private static final Logger LOG = LoggerFactory.getLogger(DefaultAssetClientFallback.class);

        @Override
        public ResponseEntity<AssetInfo> getAsset(String assetCode) {
            LOG.error("Unable to connect to DefaultAssetClient::getAsset");
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
