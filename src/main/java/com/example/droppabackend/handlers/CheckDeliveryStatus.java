package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.droppabackend.Services.PackageServices;

import java.sql.SQLException;

public class CheckDeliveryStatus implements RequestHandler<Integer, com.example.droppabackend.Models.CheckDeliveryStatus> {
    PackageServices packageServices = new PackageServices();
    @Override
    public com.example.droppabackend.Models.CheckDeliveryStatus handleRequest(Integer packageId, Context context) {

        try{
            return packageServices.checkDeliveryStatus(packageId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
