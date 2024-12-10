package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.MovieWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieWatchRepository extends JpaRepository<MovieWatch, UUID> {
    MovieWatch findByViewerIdAndMovieId(UUID viewerId, UUID movieId);
    List<MovieWatch> findByViewerId(UUID viewerId);
    List<MovieWatch> findByMovieId(UUID movieId);
}
