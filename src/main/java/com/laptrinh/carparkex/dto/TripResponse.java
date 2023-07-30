package com.laptrinh.carparkex.dto;
import lombok.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private Long tripId;

    private Integer bookedTicketNumber;

    private String carType;

    private LocalTime departureTime;

    private String destination;

    private String driver;

}
