package com.laptrinh.carparkex.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingOffResponse {

    private Long bookId;

    private String officeName;

    private String tripName;
}
