package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie create(MovieDTO movieDto) {
        Movie movie = this.convertToEntity(movieDto);
        return this.movieRepository.save(movie);
    }

    public Optional<Movie> findMovieById(UUID id) {
        return this.movieRepository.findById(id);
    }

    public List<Movie> findAllMovies() {
        Sort sort = Sort.by("title").ascending();
        return this.movieRepository.findAll(sort);
    }

    public List<Movie> getMoviesAvailable() {
        return this.movieRepository.findByAvailableTrue();
    }

    public List<Movie> getMoviesUnavailable() {
        return this.movieRepository.findByAvailableFalse();
    }

    public void markAsUnavailable(UUID movieId) {
        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));

        movie.setAvailable(false);
        movie.setUnavailableSince(LocalDate.now());

        this.movieRepository.save(movie);
    }

    public Movie convertToEntity(final MovieDTO movieDto) {
        return Movie.builder()
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .releaseYear(movieDto.getReleaseYear())
                .available(movieDto.getAvailable())
                .build();
    }

}
