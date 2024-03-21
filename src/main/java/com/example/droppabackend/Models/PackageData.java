package com.example.droppabackend.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor


public class PackageData implements Serializable {
    private String companyEmail;
    private String recipientName;
    private String recipientPhone;
    private String recipientEmail;
    private int packageWeight;
    private String deliveryAddress;
}
