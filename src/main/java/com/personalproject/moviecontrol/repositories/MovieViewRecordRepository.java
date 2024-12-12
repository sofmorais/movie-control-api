package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.MovieViewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieViewRecordRepository extends JpaRepository<MovieViewRecord, UUID> {

    @Query(value = "SELECT COUNT(mw) FROM MovieViewRecord mw " +
            "WHERE mw.movie.id = :movieId",
            nativeQuery = false)
    int getTotalViews(UUID movieId);

    @Query(value = "SELECT COUNT(mw) FROM MovieViewRecord mw " +
            "WHERE mw.viewer.id = :viewerId",
            nativeQuery = false)
    int countMoviesWatchedByViewer(UUID viewerId);

    List<MovieViewRecord> findByViewerId(UUID viewerId);

    List<MovieViewRecord> findByMovieId(UUID movieId);
}
