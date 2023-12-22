package com.github.fabriciolfj.demosns.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.sqs.config.SqsBootstrapConfiguration;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementOrdering;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;

@Import(SqsBootstrapConfiguration.class)
@Configuration
public class SqsConfig {

    @Bean
    @Primary
    public AmazonSQSAsync amazonSqsAsync(SqsProperties properties) {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(properties.getUrl(), properties.getRegion())
                )
                .build();
    }

    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {
        return SqsMessageListenerContainerFactory
                .builder()
                .configure(options -> options
                        .acknowledgementMode(AcknowledgementMode.ON_SUCCESS)
                        //.acknowledgementInterval(Duration.ofSeconds(3))
                        .acknowledgementThreshold(5)
                        .acknowledgementOrdering(AcknowledgementOrdering.ORDERED)
                )
                .sqsAsyncClient(sqsAsyncClient())
                .build();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder().build();
    }
}
