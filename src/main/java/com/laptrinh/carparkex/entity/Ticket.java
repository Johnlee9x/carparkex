package com.laptrinh.carparkex.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ticketId")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime bookingTime;

    @Column(nullable = false, length = 11)
    private String customerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId", nullable = false)
    private Trip trip;


    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
    @JoinColumn(name = "licensePlate", nullable = false)
    @JsonIgnoreProperties(value = { "application", "hibernateLazyInitializer" })
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car car;
}
