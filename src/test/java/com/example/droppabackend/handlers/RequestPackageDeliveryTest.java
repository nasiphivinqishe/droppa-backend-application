package com.example.droppabackend.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.droppabackend.Models.PackageData;
import com.example.droppabackend.commons.TestContext;
import org.junit.jupiter.api.Test;

import java.util.Date;


class RequestPackageDeliveryTest {
    RequestPackageDelivery requestPackageDelivery = new RequestPackageDelivery();
 @Test
    public void acceptPackage() {
     Context context = new TestContext();
     PackageData event = new PackageData();


     event.setRecipientName("Ummi");
     event.setRecipientPhone("0732891111");
     event.setRecipientEmail("umi@gmbh.com");
     event.setPackageWeight(6);
     event.setDeliveryAddress("19 Sho str");
     event.setCompanyEmail("nasiphi@gmail.com");

     String results = requestPackageDelivery.handleRequest(event, context);
     System.out.println("Hello There ==> " + results);

 }

}