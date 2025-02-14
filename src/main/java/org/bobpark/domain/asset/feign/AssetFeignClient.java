package org.bobpark.domain.asset.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.bobpark.domain.asset.model.AssetResponse;
import org.bobpark.domain.asset.model.AssetScriptResponse;

@FeignClient(name = "exam-tts-api", contextId = "exam-tts-api")
public interface AssetFeignClient {

    @GetMapping(path = "api/v1/assets/{assetId:\\d+}")
    AssetResponse getAsset(@PathVariable Long assetId);

    @GetMapping(path = "api/v1/assets/scripts/tts")
    List<AssetScriptResponse> textToSql(@RequestParam("query") String query);
}
