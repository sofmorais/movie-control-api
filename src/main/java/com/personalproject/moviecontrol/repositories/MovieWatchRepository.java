package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.MovieWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieWatchRepository extends JpaRepository<MovieWatch, UUID> {

    @Query(value = "SELECT COUNT(mw) FROM MovieWatch mw " +
            "WHERE mw.movie.id = :movieId",
            nativeQuery = false)
    int getTotalViews(UUID movieId);

    @Query(value = "SELECT COUNT(mw) FROM MovieWatch mw " +
            "WHERE mw.viewer.id = :viewerId",
            nativeQuery = false)
    int countMoviesWatchedByViewer(UUID viewerId);

    List<MovieWatch> findByViewerId(UUID viewerId);

    List<MovieWatch> findByMovieId(UUID movieId);
}
