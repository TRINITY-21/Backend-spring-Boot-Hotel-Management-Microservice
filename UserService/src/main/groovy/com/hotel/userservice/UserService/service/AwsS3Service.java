package com.hotel.userservice.UserService.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hotel.userservice.UserService.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class AwsS3Service {

    private static final Logger logger = LoggerFactory.getLogger(AwsS3Service.class);

    private final AmazonS3 s3Client;
    private final String bucketName = "trinity-hotel-images";

    public AwsS3Service(
            @Value("${aws.s3.access.key}") String accessKey,
            @Value("${aws.s3.secret.key}") String secretKey) {

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    public String saveImageToS3(MultipartFile photo) {
        if (photo == null || photo.isEmpty()) {
            throw new BaseException("File is empty or null");
        }

        String s3Filename = photo.getOriginalFilename();

        try (InputStream inputStream = photo.getInputStream()) {
            // Set metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(photo.getContentType());
            metadata.setContentLength(photo.getSize());

            // Upload file
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Filename, inputStream, metadata);
            s3Client.putObject(putObjectRequest);

            // Return S3 file URL
            return "https://" + bucketName + ".s3.amazonaws.com/" + s3Filename;
        } catch (Exception e) {
            logger.error("Error uploading image to S3: {}", e.getMessage(), e);
            throw new BaseException("Unable to upload image to S3 bucket: " + e.getMessage());
        }
    }
}
