package com.personalproject.moviecontrol.dtos;

import lombok.Data;

@Data
public class MovieDTO {
    private String title;
    private String genre;
    private int releaseYear;
    private Boolean available = true;
}
