package com.personalproject.moviecontrol.dtos;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class MovieWatchDTO {
    private UUID movieId;
    private UUID viewerId;
}
