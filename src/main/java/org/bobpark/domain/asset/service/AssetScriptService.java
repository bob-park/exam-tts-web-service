package org.bobpark.domain.asset.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.bobpark.domain.asset.feign.AssetFeignClient;
import org.bobpark.domain.asset.model.AssetScriptResponse;
import org.bobpark.domain.asset.model.TextToSqlRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssetScriptService {

    private final AssetFeignClient assetClient;

    public List<AssetScriptResponse> textToSql(TextToSqlRequest request) {
        return assetClient.textToSql(request.query());
    }

}
