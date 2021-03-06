package com.yogi.aws.sns;

public interface PublisherService {
    //
    // Name of the topic
    //
    public static final String TOPIC_INTERVIEWSTATUS = "interview_status";
    //
    // Publish Message API
    //
    void publish(String message, String topic) throws TopicNotSupportedException;
}