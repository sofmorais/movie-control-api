package com.personalproject.moviecontrol.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MovieDTO {
    private UUID id;
    private String title;
    private String genre;
    private int releaseYear;
}
