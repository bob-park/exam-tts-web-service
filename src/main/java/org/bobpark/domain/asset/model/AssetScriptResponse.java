package org.bobpark.domain.asset.model;

import java.time.LocalDateTime;

public record AssetScriptResponse(Long id,
                                  String type,
                                  Long assetId,
                                  Long inPoint,
                                  Long outPoint,
                                  String contents,
                                  String description,
                                  LocalDateTime createdDate,
                                  String createdBy,
                                  LocalDateTime lastModifiedDate,
                                  String lastModifiedBy){


}
