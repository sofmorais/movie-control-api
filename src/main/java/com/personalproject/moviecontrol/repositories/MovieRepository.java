package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findByAvailableTrue();

    List<Movie> findByAvailableFalse();
}
