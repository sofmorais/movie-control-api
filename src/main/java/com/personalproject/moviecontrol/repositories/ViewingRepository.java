package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViewingRepository extends JpaRepository<Viewing, UUID> {
}
