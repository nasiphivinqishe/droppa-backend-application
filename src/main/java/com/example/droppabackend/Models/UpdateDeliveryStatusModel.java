package com.example.droppabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryStatusModel {
    private int deliveryRequestId;
    private String newStatus;
    private LocalDateTime dateUpdated;
    private String estimatedDelivery;
}
