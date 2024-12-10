package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieWatchDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.services.MovieWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movie-watch")
public class MovieWatchController {

    @Autowired
    private MovieWatchService movieWatchService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MovieWatchDTO movieWatch) {
        this.movieWatchService.create(movieWatch);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{viewerId}")
    public ResponseEntity<List<Movie>> findMoviesByViewerId(UUID viewerId) {
        return ResponseEntity.ok(this.movieWatchService.findMoviesWatchedByViewerId(viewerId));
    }

    @GetMapping(value = "/{movieId}")
    public ResponseEntity<List<Viewer>> findViewersByMovieId(UUID movieId) {
        return ResponseEntity.ok(this.movieWatchService.findViewersByMovieId(movieId));
    }

}
