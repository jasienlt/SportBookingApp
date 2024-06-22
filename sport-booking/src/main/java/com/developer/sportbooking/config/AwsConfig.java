package com.developer.sportbooking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.s3.InMemoryBufferingS3OutputStreamProvider;
import io.awspring.cloud.s3.Jackson2JsonS3ObjectConverter;
import io.awspring.cloud.s3.S3Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class AwsConfig {
    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create({accessKeyId}, {accessKeyId1}))).build();
    }
    @Bean
    public S3Template s3Template(){
        return new S3Template(s3Client() , new InMemoryBufferingS3OutputStreamProvider(s3Client() , null) ,new Jackson2JsonS3ObjectConverter(new ObjectMapper()), S3Presigner.create());
    }
}
