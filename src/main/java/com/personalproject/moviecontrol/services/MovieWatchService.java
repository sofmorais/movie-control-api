package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.MovieViewRecordDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieViewRecord;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.repositories.MovieViewRecordRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MovieWatchService {

    @Autowired
    private MovieViewRecordRepository movieViewRecordRepository;

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieViewRecord create(MovieViewRecordDTO viewRecordDto) {
        MovieViewRecord viewing = this.convertToEntity(viewRecordDto);
        return this.movieViewRecordRepository.save(viewing);
    }

    public List<Movie> findMoviesWatchedByViewerId(UUID viewerId) {
        return this.movieViewRecordRepository.findByViewerId(viewerId)
                .stream()
                .map(MovieViewRecord::getMovie)
                .toList();
    }

    public List<Viewer> findViewersByMovieId(UUID movieId) {
        return this.movieViewRecordRepository.findByMovieId(movieId)
                .stream()
                .map(MovieViewRecord::getViewer)
                .toList();
    }

    public int getTotalViews(UUID movieId) {
        return this.movieViewRecordRepository.getTotalViews(movieId);
    }

    public int getMoviesWatchedCount(UUID viewerId) {
        return this.movieViewRecordRepository.countMoviesWatchedByViewer(viewerId);
    }

    @Transactional
    public MovieViewRecord convertToEntity(MovieViewRecordDTO movieViewRecordDto) {
        Movie movie = this.movieRepository.findById(movieViewRecordDto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        Viewer viewer = this.viewerRepository.findById(movieViewRecordDto.getViewerId())
                .orElseThrow(() -> new IllegalArgumentException("Viewer not found"));

        return MovieViewRecord.builder()
                .movie(movie)
                .viewer(viewer)
                .viewingDate(new Date())
                .build();
    }
}
