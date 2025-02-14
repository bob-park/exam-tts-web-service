package org.bobpark.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProperties(String loginSuccessUrl,
                            String logoutSuccessUrl) {
}
