package com.laptrinh.carparkex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotResponse {

    private Integer parkId;

    private Integer parkArea;

    private String parkName;

    private String parkPlace;

    private Integer parkPrice;

    private String parkStatus;


}
