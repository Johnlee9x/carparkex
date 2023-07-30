package com.laptrinh.carparkex.dto;

import lombok.*;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long ticketId;
    private String destination;
    private String licensePlate;
    private String customerName;
    private LocalTime bookingTime;

}
