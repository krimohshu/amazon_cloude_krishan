package com.sns.yogi.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

@Service
public class AmazonSNSPublisherService implements PublisherService {

    private AmazonSNS amazonSNS;
    private String snsTopicInterviewStatusARN;

    @Autowired
    public AmazonSNSPublisherService(BasicSessionCredentials sessionCredentials, String snsTopicInterviewStatusARN) {
        this.amazonSNS = AmazonSNSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
        this.snsTopicInterviewStatusARN = snsTopicInterviewStatusARN;
    }

    @Override
    public void publish(String message, String topic) throws TopicNotSupportedException {
        //
        // Get Appropriate Topic ARN
        //
        String snsTopic = getTopicARN(topic);
        //
        // Create Publish Message Request with TopicARN and Message
        //
        PublishRequest publishRequest = new PublishRequest(snsTopic, message);
        //
        // Publish the message
        //
        PublishResult publishResult = this.amazonSNS.publish(publishRequest);
        //
        // Evaluate the result: Print MessageId of message published to SNS topic
        //
        System.out.println("MessageId - " + publishResult.getMessageId());

    }

    private String getTopicARN(String topic) throws TopicNotSupportedException {
        switch(topic) {
            case TOPIC_INTERVIEWSTATUS:
                return this.snsTopicInterviewStatusARN;

            default:
                throw new TopicNotSupportedException("No matching topic supported!");
        }
    }
}