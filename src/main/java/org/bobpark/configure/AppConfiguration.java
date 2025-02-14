package org.bobpark.configure;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import org.bobpark.configure.properties.AppProperties;

@RequiredArgsConstructor
@EnableConfigurationProperties(AppProperties.class)
@Configuration
public class AppConfiguration {
}
