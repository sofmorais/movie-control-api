package com.personalproject.moviecontrol;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.services.MovieService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários da Classe - MovieServiceTest")
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;
    private MovieDTO movieDto;

    @BeforeEach
    public void setUp() {
        movieDto = new MovieDTO();
        movieDto.setTitle("Inception");
        movieDto.setGenre("Sci-Fi");
        movieDto.setReleaseYear(2010);

        movie = Movie.builder()
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .releaseYear(movieDto.getReleaseYear())
                .build();
    }

    @Test
    public void create() {

        when(this.movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie createdMovie = this.movieService.create(movieDto);

        assertNotNull(createdMovie);
        assertEquals("Inception", createdMovie.getTitle());

        verify(this.movieRepository, times(1)).save(any(Movie.class));
        verifyNoMoreInteractions(this.movieRepository);
    }

    @Test
    public void findMovieById() {
        Movie movie = new Movie();
        movie.setId(UUID.randomUUID());
        movie.setTitle("Inception");

        when(this.movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        Optional<Movie> result = this.movieService.findMovieById(movie.getId());

        assertTrue(result.isPresent());
        assertEquals("Inception", result.get().getTitle());
        verify(this.movieRepository, times(1)).findById(movie.getId());
    }

    @Test
    public void findAllMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("Inception");
        Movie movie2 = new Movie();
        movie2.setTitle("Interstellar");

        List<Movie> movieList = Arrays.asList(movie1, movie2);

        when(this.movieRepository.findAll(any(Sort.class))).thenReturn(movieList);

        List<Movie> result = this.movieService.findAllMovies();

        assertEquals(2, result.size());
        verify(this.movieRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    public void delete() {
        UUID movieId = UUID.randomUUID();

        doNothing().when(this.movieRepository).deleteById(movieId);

        this.movieService.delete(movieId);

        verify(this.movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    public void convertToEntity() {
        Movie result = this.movieService.convertToEntity(movieDto);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(movie.getTitle());
    }

}
