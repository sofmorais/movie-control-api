package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.MovieWatchDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.repositories.MovieWatchRepository;
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
    private MovieWatchRepository movieWatchRepository;

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieWatch create(MovieWatchDTO movieWatchDto) {
        MovieWatch viewing = this.convertToEntity(movieWatchDto);
        return this.movieWatchRepository.save(viewing);
    }

    public List<Movie> findMoviesWatchedByViewerId(UUID viewerId) {
        return this.movieWatchRepository.findByViewerId(viewerId)
                .stream()
                .map(MovieWatch::getMovie)
                .toList();
    }

    public List<Viewer> findViewersByMovieId(UUID movieId) {
        return this.movieWatchRepository.findByMovieId(movieId)
                .stream()
                .map(MovieWatch::getViewer)
                .toList();
    }

    public int getTotalViews(UUID movieId) {
        return this.movieWatchRepository.getTotalViews(movieId);
    }

    public int getMoviesWatchedCount(UUID viewerId) {
        return this.movieWatchRepository.countMoviesWatchedByViewer(viewerId);
    }

    @Transactional
    public MovieWatch convertToEntity(MovieWatchDTO movieWatchDto) {
        Movie movie = this.movieRepository.findById(movieWatchDto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        Viewer viewer = this.viewerRepository.findById(movieWatchDto.getViewerId())
                .orElseThrow(() -> new IllegalArgumentException("Viewer not found"));

        return MovieWatch.builder()
                .movie(movie)
                .viewer(viewer)
                .viewingDate(new Date())
                .build();
    }
}
