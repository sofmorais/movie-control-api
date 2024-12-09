package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends CrudRepository<Movie, UUID> {
}
