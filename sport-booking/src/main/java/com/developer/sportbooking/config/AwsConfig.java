package com.developer.sportbooking.config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import javax.imageio.ImageIO;


public class AwsConfig {

    private static final String BUCKET = "sportbookingapp";
    private static final String folderName1 = "payment_screenshots";
    private static final String folderName2 = "court_url";

    //@Value("${aws.accessKeyId}")
    private static String accessKeyId = "";

    //@Value("${aws.secretAccessKey}")
    private static String secretAccessKey = "";

    public static String folderDirect(Integer folderNum) {
        return switch (folderNum) {
            case 1 -> folderName1;
            case 2 -> folderName2;
            default -> null;
        };
    }

    // PUT

    public static void uploadFile(String fileName, InputStream inputStream, String folderDir)
            throws AwsServiceException, SdkClientException, IOException {


        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();



        String key = folderDir + "/" + fileName;
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

    public static void createFolder(String folderName, String folderDir)
            throws AwsServiceException, SdkClientException, IOException {


        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();



        String key = folderDir + "/" + folderName + "/";
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .contentType("image/*")
                .build();

        client.putObject(request,
                RequestBody.empty());


        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

        waitResponse.matched().response().ifPresent(System.out::println);
        System.out.println("Folder " + folderName + " was uploaded.");
    }

    // COPY & DELETE
    public static void moveFile(String fileName, String fromDir, String toDir) {
        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();



        // Copy file into the new directory

        String fromKey = fromDir + "/" + fileName;
        String toKey = toDir + "/" + fileName;
        CopyObjectRequest request = CopyObjectRequest.builder()
                .sourceBucket(fromKey)
                .destinationBucket(toKey)
                .build();
        client.copyObject(request);

        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(toKey)
                .build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

        waitResponse.matched().response().ifPresent(System.out::println);
        System.out.println("File " + fileName + " was moved successfully.");

        // Delete old file

        DeleteObjectRequest request1 = DeleteObjectRequest.builder()
                .bucket(BUCKET)
                .key(fromKey)
                .build();
        client.deleteObject(request1);

    }

    // GET

    public static String getImage(String fileName, String folderDir)
            throws AwsServiceException, SdkClientException, IOException {


        AwsCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        S3Client client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();



        String key = folderDir + "/" + fileName;
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = client.getObjectAsBytes(request);
        byte[] data = objectBytes.asByteArray();

        // create the object of ByteArrayInputStream class
        // and initialized it with the byte array.
        // ByteArrayInputStream inStreambj = new ByteArrayInputStream(data);

        // read image from byte array
        // BufferedImage finalImage = ImageIO.read(inStreambj);

        String imageAsBase64 = Base64.getEncoder().encodeToString(data);

        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

        waitResponse.matched().response().ifPresent(System.out::println);
        System.out.println("Folder " + fileName + " was successfully retrieved.");
        return imageAsBase64;
    }

}
