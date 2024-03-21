package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.commons.TestContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetDeliveryRequestByIdTest {
    GetDeliveryRequestById getDeliveryRequestById = new GetDeliveryRequestById();
    @Test
    public void getDeliveryReqById() {
        Context context = new TestContext();
        int integer = 2;

        PackageData results = getDeliveryRequestById.handleRequest(integer, context);
        System.out.println(results);
    }
}