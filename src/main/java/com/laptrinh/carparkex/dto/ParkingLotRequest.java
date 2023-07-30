package com.laptrinh.carparkex.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ParkingLotRequest {

    @NotNull
    @Column(nullable = false, length = 20)
    @Min(value = 0)
    private Integer parkArea;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String parkName;

    @NotBlank
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11 )
    private String parkPlace;

    @NotNull
    @Column(nullable = false, length = 20)
    @Min(value = 0)
    private Long parkPrice;


}
