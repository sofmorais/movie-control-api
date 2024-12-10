package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Year;
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
        Sort sort = Sort.by("name").ascending();
        return this.movieRepository.findAll(sort);
    }

    public void delete(UUID movieId) {
        this.movieRepository.deleteById(movieId);
    }

    public Movie convertToEntity(final MovieDTO movieDto) {
        return Movie.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .releaseYear(movieDto.getReleaseYear())
                .build();
    }
}
