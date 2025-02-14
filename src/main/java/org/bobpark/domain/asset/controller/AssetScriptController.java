package org.bobpark.domain.asset.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.bobpark.domain.asset.model.AssetScriptResponse;
import org.bobpark.domain.asset.model.TextToSqlRequest;
import org.bobpark.domain.asset.service.AssetScriptService;

@RequiredArgsConstructor
@RestController
@RequestMapping("assets/scripts")
public class AssetScriptController {

    private final AssetScriptService assetScriptService;

    @PostMapping(path = "tts")
    public List<AssetScriptResponse> textToSql(@RequestBody TextToSqlRequest request) {
        return assetScriptService.textToSql(request);
    }

}
