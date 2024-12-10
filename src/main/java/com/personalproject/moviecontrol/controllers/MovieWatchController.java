package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieWatchDTO;
import com.personalproject.moviecontrol.models.Movie;
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

    @GetMapping(value = "/{viewerId}/movies")
    public ResponseEntity<List<Movie>> findMoviesByViewerId(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.movieWatchService.findMoviesWatchedByViewerId(viewerId));
    }

    @GetMapping(value = "/{movieId}/viewers")
    public ResponseEntity<List<Viewer>> findViewersByMovieId(@PathVariable UUID movieId) {
        return ResponseEntity.ok(this.movieWatchService.findViewersByMovieId(movieId));
    }

    @GetMapping("/movie/{movieId}/total-views")
    public ResponseEntity<Integer> getTotalViews(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieWatchService.getTotalViews(movieId));
    }

    @GetMapping("/viewer/{viewerId}/movies-watched")
    public ResponseEntity<Integer> getMoviesWatchedCount(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.movieWatchService.getMoviesWatchedCount(viewerId));
    }
}
