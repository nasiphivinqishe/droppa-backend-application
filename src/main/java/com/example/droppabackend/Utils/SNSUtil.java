package com.example.droppabackend.Utils;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public class SNSUtil {
    public void sendSMS(String message, String phoneNumber) {
        SnsClient snsClient = SnsClient.builder()
                .region(Region.AF_SOUTH_1)
                .build();

        PublishResponse response = snsClient.publish(PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build());

        System.out.println("Message sent successfully--" + response.messageId());
    }
}
