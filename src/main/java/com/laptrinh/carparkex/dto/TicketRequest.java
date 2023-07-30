package com.laptrinh.carparkex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String customerName;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true, length = 50)
    private String licensePlate;

    private Long tripId;

    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime bookingTime;
}
