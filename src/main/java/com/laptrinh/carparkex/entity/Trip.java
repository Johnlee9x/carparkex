package com.laptrinh.carparkex.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;


    @Column(columnDefinition = "int default(0)", nullable = false, length = 11)
    @Min(value = 0)
    private Integer bookedTicketNumber = 0;

    @NotBlank(message = "carType is mandatory")
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11)
    private String carType;

    @NotNull(message = "departureDate is mandatory")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @NotNull(message = "departureDate is mandatory")
    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime departureTime;

    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String destination;

    @NotBlank
    @Column(nullable = false, length = 11)
    @Size(min = 3, max = 11)
    private String driver;

    @NotNull
    @Column(columnDefinition = "int default(1)", length = 11)
    @Min(value = 1, message = "value maxOnlineTicketNumber cannot less than 0")
    private Integer maxOnlineTicketNumber;

    @ToString.Exclude

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingOffice> bookingOffices;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Collection<Ticket> tickets;
}
