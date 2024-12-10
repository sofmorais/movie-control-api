package com.personalproject.moviecontrol.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MovieDTO {
    private String title;
    private String genre;
    private int releaseYear;
}
