package org.bobpark.domain.asset.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.bobpark.domain.asset.model.AssetResponse;
import org.bobpark.domain.asset.service.AssetService;

@RequiredArgsConstructor
@RestController
@RequestMapping("assets")
public class AssetController {

    private final AssetService assetService;

    @GetMapping(path = "{assetId:\\d+}")
    public AssetResponse getAsset(@PathVariable Long assetId) {
        return assetService.getAsset(assetId);
    }

}
