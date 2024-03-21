package com.example.droppabackend.Utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SNSUtil {
    public void sendSMS(String message, String phoneNumber) {
        AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build();

        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber));

        System.out.println("Message sent successfully--" + result.getMessageId());
    }
}
