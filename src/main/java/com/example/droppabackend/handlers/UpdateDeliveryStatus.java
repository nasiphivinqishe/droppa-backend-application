package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.droppabackend.Models.UpdateDeliveryStatusModel;
import com.example.droppabackend.Services.PackageServices;

public class UpdateDeliveryStatus implements RequestHandler <UpdateDeliveryStatusModel, String> {
    PackageServices packageServices = new PackageServices();

    @Override
    public String handleRequest(UpdateDeliveryStatusModel event, Context context) {
        try {
            packageServices.updateDeliveryStatus(event);
            return "Successfully updated delivery status !.";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
