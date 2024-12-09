package com.personalproject.moviecontrol.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
public class ViewerDTO {
    private UUID id;
    private String name;
}

