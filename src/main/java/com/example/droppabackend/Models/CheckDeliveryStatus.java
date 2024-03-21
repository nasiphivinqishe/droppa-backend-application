package com.example.droppabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckDeliveryStatus implements Serializable {
    private String status;
    private String estimatedDateOfDelivery;

}
