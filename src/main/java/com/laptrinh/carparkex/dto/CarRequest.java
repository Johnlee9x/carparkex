package com.laptrinh.carparkex.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    @Column(unique = true, length = 50)
    @Size(min = 3, max = 50)
    @NotBlank
    private String licensePlate;

    @NotBlank
    @Column(nullable = false, length = 11)
    private String carColor;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(length = 50, nullable = false)
    private String carType;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String company;
    private Long parkId;
}
