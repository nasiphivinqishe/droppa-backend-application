package com.example.droppabackend.Utils;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.*;

@Configuration
public class SESUtil {

    Region region = Region.US_EAST_1;

    public SesV2Client createSesClient() {
        SesV2Client sesv2Client = SesV2Client.builder().region(region).credentialsProvider(ProfileCredentialsProvider.create()).build();
        return sesv2Client;
    }

    public void send(String emailBody, String recipient) {
        String sender = "nasiphivinqishe@gmail.com";
        String subject = "Status update! ";
        Destination destination = Destination.builder().toAddresses(recipient).build();
        Content content = Content.builder().data(emailBody).build();

        Content sub = Content.builder().data(subject).build();
        Body body = Body.builder().html(content).build();

        Message msg = Message.builder().subject(sub).body(body).build();
        EmailContent emailContent = EmailContent.builder().simple(msg).build();

        SendEmailRequest emailRequest = SendEmailRequest.builder().destination(destination).content(emailContent).fromEmailAddress(sender).build();

        try {
            SesV2Client sesClient = createSesClient();
            sesClient.sendEmail(emailRequest);
            System.out.println("Email sent successfully to: " + recipient);
        } catch (SdkClientException e) {
            // Log the error
            System.err.println("Error occurred while sending email:");
            e.printStackTrace();
            // You might want to throw an exception or handle it according to your application's logic
            throw new RuntimeException("Error occurred while sending email", e);
        }
    }
}
