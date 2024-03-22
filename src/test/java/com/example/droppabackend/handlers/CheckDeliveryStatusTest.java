//package com.example.droppabackend.handlers;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.example.droppabackend.Models.DeliveryStatus;
//import com.example.droppabackend.commons.TestContext;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CheckDeliveryStatusTest {
//    CheckDeliveryStatus checkDeliveryStatus = new CheckDeliveryStatus();
//
//        @Test
//        public void doDeliveryReq(){
//            Context context = new TestContext();
//            int packageId = 1;
//
//            String results = String.valueOf(checkDeliveryStatus.handleRequest(packageId, context));
//            System.out.println("Hello There ==> " + results);
//
//        }
//}
