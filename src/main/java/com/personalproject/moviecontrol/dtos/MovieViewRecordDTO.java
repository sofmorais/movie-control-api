package com.personalproject.moviecontrol.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MovieViewRecordDTO {
    private UUID movieId;
    private UUID viewerId;
}
