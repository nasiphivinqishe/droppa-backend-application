package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.droppabackend.Models.DeliveryStatus;
import com.example.droppabackend.commons.TestContext;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;


class DoDeliveveryRequestTest {
    DoDeliveveryRequest doDeliveveryRequest = new DoDeliveveryRequest();

    @Test
    public void doDeliveryReq(){
        Context context =  new TestContext();
        DeliveryStatus event = new DeliveryStatus();

        event.setDeliveryRequestId(1);
        event.setDateReceived(LocalDateTime.now());
        event.setStatus("DELIVERED");
        event.setEstimatedDateOfDelivery("27 March 2024");

        String results = doDeliveveryRequest.handleRequest(event, context);
        System.out.println("Hello There ==> " + results);

    }

}