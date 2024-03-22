package com.example.droppabackend.handlers;


import com.amazonaws.services.lambda.runtime.Context;
import com.example.droppabackend.Models.UpdateDeliveryStatusModel;
import com.example.droppabackend.commons.TestContext;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

class UpdateDeliveryStatusTest {
    UpdateDeliveryStatus updateDeliveryStatus = new UpdateDeliveryStatus();

    @Test
    public void updateStatus() {
        Context context = new TestContext();
        UpdateDeliveryStatusModel event = new UpdateDeliveryStatusModel();

        event.setDeliveryRequestId(1);
        event.setNewStatus("COLLECTED");
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
        event.setDateUpdated(localDateTime);
        event.setEstimatedDelivery("27 March 2024");

        String results = updateDeliveryStatus.handleRequest(event, context);
        System.out.println("Hello There ==> " + results);

    }
}