package com.itau.dailybanking.cancelamento.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

import java.net.URI;

@Configuration
public class SqsConfig {

    private static final Logger log = LoggerFactory.getLogger(SqsConfig.class);

    @Value("${aws.region}")
    private String region;

    @Value("${aws.sqs.endpoint:}")
    private String endpoint;

    @Bean
    public SqsClient sqsClient() {
        log.info("🔌 Criando SqsClient → region={} endpointOverride={}", region, endpoint);

        var builder = SqsClient.builder()
                .region(Region.of(region));

        if (endpoint != null && !endpoint.isBlank()) {
            builder = builder.endpointOverride(URI.create(endpoint));
        }

        return builder.build();
    }

}
