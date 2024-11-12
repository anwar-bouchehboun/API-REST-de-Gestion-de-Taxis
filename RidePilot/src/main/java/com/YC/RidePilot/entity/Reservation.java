package com.YC.RidePilot.entity;

import com.YC.RidePilot.enums.StatutReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date and time are required")
    private LocalDateTime dateHeure;

    @NotNull(message = "Departure address is required")
    private String adresseDepart;

    @NotNull(message = "Arrival address is required")
    private String adresseArrivee;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private Double prix;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private StatutReservation statut;

    @NotNull(message = "Distance is required")
    @Min(value = 0, message = "Distance must be positive")
    private Double distanceKm;


    @NotNull(message = "Availability start time is required")
    private LocalDateTime heureDebutCourse;

    @NotNull(message = "Availability start time is required")
    private LocalDateTime heureFinCourse;


    @Embedded
    private Address adress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chauffeur_id")
    private Chauffeur chauffeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

}
