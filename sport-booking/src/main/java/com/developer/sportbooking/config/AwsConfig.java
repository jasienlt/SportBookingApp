package com.developer.sportbooking.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;


public class AwsConfig {

    private static final String BUCKET = "sportbookingapp";
    private static final String folderName = "payment_screenshots";

    //@Value("${aws.accessKeyId}")
    private static String accessKeyId = "";

    //@Value("${aws.secretAccessKey}")
    private static String secretAccessKey = "";


    public static void uploadFile(String fileName, InputStream inputStream)
            throws AwsServiceException, SdkClientException, IOException {


        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();



        String key = folderName + "/" + fileName;
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .contentType("image/*")
                .build();

        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));


        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

        waitResponse.matched().response().ifPresent(System.out::println);
        System.out.println("File " + fileName + " was uploaded.");
    }

}
