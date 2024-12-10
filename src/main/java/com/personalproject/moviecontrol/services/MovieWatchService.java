package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.MovieWatchDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import com.personalproject.moviecontrol.repositories.MovieWatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Movie> getMoviesWatchedByViewer(UUID viewerId) {
        return this.movieWatchRepository.findByViewerId(viewerId)
                .stream()
                .map(MovieWatch::getMovie)
                .collect(Collectors.toList());
    }

    public List<Viewer> getViewersWhoWatchedMovie(UUID movieId) {
        return movieWatchRepository.findByMovieId(movieId)
                .stream()
                .map(MovieWatch::getViewer)
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieWatch convertToEntity(MovieWatchDTO movieWatchDto) {
        Movie movie = this.movieRepository.findById(movieWatchDto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        Viewer viewer = this.viewerRepository.findById(movieWatchDto.getViewerId())
                .orElseThrow(() -> new IllegalArgumentException("Viewer not found"));

        return MovieWatch.builder()
                .id(movieWatchDto.getId())
                .movie(movie)
                .viewer(viewer)
                .build();
    }
}
