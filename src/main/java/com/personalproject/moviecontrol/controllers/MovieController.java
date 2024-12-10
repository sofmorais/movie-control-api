package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody MovieDTO movie) {
        return ResponseEntity.ok(this.movieService.create(movie));
    }

    @GetMapping("movie/{movieId}")
    public ResponseEntity<Optional<Movie>> findMovieById(@PathVariable UUID movieId) {
        return ResponseEntity.ok(this.movieService.findMovieById(movieId));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovie(){
        return ResponseEntity.ok(this.movieService.findAllMovies());
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable UUID movieId) {
        this.movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }

}
