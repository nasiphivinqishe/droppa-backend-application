package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.droppabackend.Models.DeliveryStatus;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.Services.PackageServices;

import java.sql.SQLException;

public class DoDeliveveryRequest implements RequestHandler <DeliveryStatus, String> {
    PackageServices packageServices = new PackageServices();
    @Override
    public String handleRequest(DeliveryStatus event, Context context) {
        System.out.println("Okay check lambda: "+ event);
        try {
            packageServices.doDeliveryRequest(event);
            return "Successfully accepted package.!!.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
