package com.laptrinh.carparkex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarResponse {

    private String licensePlate;

    private String carColor;

    private String carType;

    private String company;

    private String parkingLotName;
}
