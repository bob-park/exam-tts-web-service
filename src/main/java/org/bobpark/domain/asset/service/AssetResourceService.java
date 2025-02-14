package org.bobpark.domain.asset.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.exception.ServiceRuntimeException;
import com.malgn.common.type.asset.AssetFileType;

import org.bobpark.configure.storage.properties.StorageProperties;
import org.bobpark.domain.asset.feign.AssetFeignClient;
import org.bobpark.domain.asset.model.AssetFileResponse;
import org.bobpark.domain.asset.model.AssetResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssetResourceService {

    private static final DataSize DEFAULT_PARTIAL_SIZE = DataSize.ofMegabytes(10);

    private final StorageProperties properties;

    private final AssetFeignClient assetClient;

    public ResourceRegion streaming(HttpRange range, Long assetId) {

        AssetResponse asset = assetClient.getAsset(assetId);
        AssetFileResponse file = getFile(asset.files());

        Resource resource = null;

        long start = 0;
        long end = 0;
        long rangeLength = 0;

        try {

            String absolutePath =
                properties.location().getFile().getAbsolutePath() + File.separatorChar
                    + file.filePath();

            resource = new FileSystemResource(absolutePath);

            long contentLength = resource.contentLength();
            long chunkSize = DEFAULT_PARTIAL_SIZE.toBytes();
            rangeLength = contentLength;

            if (range != null) {
                start = range.getRangeStart(contentLength);
                end = range.getRangeEnd(contentLength);
                rangeLength = Math.min(chunkSize, end - start + 1);
            }

        } catch (IOException e) {
            throw new ServiceRuntimeException(e);
        }

        return new ResourceRegion(resource, start, rangeLength);
    }

    private AssetFileResponse getFile(List<AssetFileResponse> files) {
        return files.stream()
            .filter(file -> file.fileType() == AssetFileType.HI_RES)
            .findAny()
            .orElseThrow(() -> new NotFoundException("file not found"));
    }

}
