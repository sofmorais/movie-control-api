package com.personalproject.moviecontrol.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "releaseYear")
    private int releaseYear;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "unavailable_since", nullable = true)
    private LocalDate unavailableSince;
}