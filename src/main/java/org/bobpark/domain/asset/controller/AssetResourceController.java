package org.bobpark.domain.asset.controller;

import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.bobpark.domain.asset.service.AssetResourceService;

@RequiredArgsConstructor
@RestController
@RequestMapping("assets/{assetId:\\d+}/resource")
public class AssetResourceController {

    private final AssetResourceService assetResourceService;

    @GetMapping(path = "")
    public ResponseEntity<ResourceRegion> getResource(@PathVariable long assetId,
        @RequestHeader HttpHeaders reqHeaders) {

        HttpRange httpRange = reqHeaders.getRange().stream().findFirst().orElse(null);

        ResourceRegion resourceRegion = assetResourceService.streaming(httpRange, assetId);

        Resource resource = resourceRegion.getResource();

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .headers(httpHeaders -> {
                httpHeaders.setContentType(
                    MediaTypeFactory.getMediaType(resource)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM));

                httpHeaders.setContentDisposition(
                    ContentDisposition.attachment()
                        .filename(resource.getFilename(), StandardCharsets.UTF_8)
                        .build());
            })
            .body(resourceRegion);

    }

}
