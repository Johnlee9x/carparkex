package com.laptrinh.carparkex.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingOffRequest {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "birthday must be a date in the past")
    private Date endContactDeadline;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "birthday must be a date in the past")
    private Date startContactDeadline;

    @NotBlank
    @Size(max = 50)
    private String officeName;

    @NotNull
    private Integer officePhone;

    @NotBlank
    @Size(max = 50)
    private String officePlace;

    @NotNull
    @Min(value = 0)
    private Double officePrice;

    private Long tripId;
}
