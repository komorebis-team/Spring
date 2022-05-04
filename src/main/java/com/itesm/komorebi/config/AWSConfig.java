package com.itesm.komorebi.config;import com.amazonaws.auth.AWSCredentials;import com.amazonaws.auth.BasicAWSCredentials;import com.amazonaws.services.s3.AmazonS3;import org.springframework.beans.factory.annotation.Value;import java.io.InputStream;public class AWSConfig {

    @Value("${komorebi.app.accessKeyId}")
    private String accessKeyId;

    @Value("${komorebi.app.secretAccessKey}")
    private String secretAccessKey;

    @Value("${komorebi.app.bucketName}")
    private String bucketName;

    @Value("${komorebi.app.region}")
    private String region;

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials =
        new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return AmazonS3ClientBuilder().standard().withRegion(region).WithCredentials(new AWSStativCredentialsProvider(aw))
    }
}
