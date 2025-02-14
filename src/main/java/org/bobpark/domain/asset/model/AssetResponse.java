package org.bobpark.domain.asset.model;

import java.math.BigDecimal;
import java.util.List;

public record AssetResponse(Long id,
                            String title,
                            BigDecimal videoFps,
                            Long videoDuration,
                            List<AssetFileResponse> files) {

}
