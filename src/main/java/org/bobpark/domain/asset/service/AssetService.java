package org.bobpark.domain.asset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.bobpark.domain.asset.feign.AssetFeignClient;
import org.bobpark.domain.asset.model.AssetResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssetService {

    private final AssetFeignClient assetClient;

    public AssetResponse getAsset(Long assetId) {
        return assetClient.getAsset(assetId);
    }

}
