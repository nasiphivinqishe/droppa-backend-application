package com.example.droppabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatus implements Serializable {
    private int deliveryRequestId;
    private LocalDateTime dateReceived;
    private String status;
    private String estimatedDateOfDelivery;
}
