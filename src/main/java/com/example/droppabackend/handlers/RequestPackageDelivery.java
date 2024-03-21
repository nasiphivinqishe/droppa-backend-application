package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.droppabackend.Services.PackageServices;
import com.example.droppabackend.Models.PackageData;

import java.sql.SQLException;

public class RequestPackageDelivery implements RequestHandler<PackageData, String>{
    PackageServices packageService = new PackageServices();
    @Override
    public String handleRequest(PackageData event, Context context) {
        try {
            System.out.println("Okay check lambda: "+ event);

            packageService.requestPackageDelivery(event);
            return "Sucessfully accepted package.!!.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
