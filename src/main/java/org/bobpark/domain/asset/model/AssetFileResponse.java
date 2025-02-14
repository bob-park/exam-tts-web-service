package org.bobpark.domain.asset.model;

import lombok.Builder;

import com.malgn.common.type.asset.AssetFileType;

@Builder
public record AssetFileResponse(Long id,
                                AssetFileType fileType,
                                String filePath,
                                Long fileSize) {

}
