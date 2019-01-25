package s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public class S3AccessByJava {

    public static void main(String[] args) {

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAI6CA7U5HRNSAVKPA",
                "2R3out3pUGQRHqfeuTk3HjHIpSFX0I6RFxJ2A7pc"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        String bucketName = "krishan-bucket-of-testing-ifs";
        if(s3client.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
                    + " Try again with a different Bucket name.");
           // return;
        }
       // s3client.createBucket(bucketName);
        printAllS3Bucket(s3client);
        deleteS3Bucket(s3client);
        System.out.println();
        printAllS3Bucket(s3client);
    }

    public static void printAllS3Bucket(AmazonS3 s3client){

        List<com.amazonaws.services.s3.model.Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
    }

    public static void deleteS3Bucket(AmazonS3 s3client){

        try {
            s3client.deleteBucket("krishan-bucket-of-testing-ifs");
        } catch (AmazonServiceException e) {
            System.err.println("e.getErrorMessage()");
            return;
        }
    }

}
