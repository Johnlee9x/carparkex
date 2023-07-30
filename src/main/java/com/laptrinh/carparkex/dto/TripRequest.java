package com.laptrinh.carparkex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {

    @NotBlank
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11)
    private String carType;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime departureTime;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String destination;

    @NotBlank(message = "driver is mandatory")
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11)
    private String driver;

    @NotNull
    @Column(columnDefinition = "int default(1)", length = 11)
    @Min(value = 1)
    private Integer maxOnlineTicketNumber;


}
