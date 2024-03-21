package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.Services.PackageServices;

import java.sql.SQLException;

public class GetDeliveryRequestById implements RequestHandler <Integer, PackageData> {
    PackageServices packageServices = new PackageServices();
    @Override
    public PackageData handleRequest(Integer integer, Context context) {
        try {
            System.out.println(integer);
            return  packageServices.getDeliveryRequestById(integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
