package com.personalproject.moviecontrol;

import com.personalproject.moviecontrol.dtos.MovieWatchDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.repositories.MovieWatchRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import com.personalproject.moviecontrol.services.MovieWatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários da Classe - MovieWatchServiceTest")
class MovieWatchServiceTest {

    @Mock
    private MovieWatchRepository movieWatchRepository;

    @Mock
    private ViewerRepository viewerRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieWatchService movieWatchService;

    private Movie movie;
    private Viewer viewer;
    private MovieWatch movieWatch;
    private MovieWatchDTO movieWatchDto;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(UUID.randomUUID());

        viewer = new Viewer();
        viewer.setId(UUID.randomUUID());

        movieWatch = MovieWatch.builder()
                .movie(movie)
                .viewer(viewer)
                .viewingDate(new Date())
                .build();

        movieWatchDto = new MovieWatchDTO();
        movieWatchDto.setMovieId(movie.getId());
        movieWatchDto.setViewerId(viewer.getId());
    }

    @Test
    void create() {
        when(this.movieRepository.findById(movieWatchDto.getMovieId())).thenReturn(Optional.of(movie));
        when(this.viewerRepository.findById(movieWatchDto.getViewerId())).thenReturn(Optional.of(viewer));
        when(this.movieWatchRepository.save(any(MovieWatch.class))).thenReturn(movieWatch);

        MovieWatch created = this.movieWatchService.create(movieWatchDto);

        assertThat(created).isNotNull();
        assertThat(created.getMovie()).isEqualTo(movie);
        assertThat(created.getViewer()).isEqualTo(viewer);
        verify(this.movieRepository).findById(movieWatchDto.getMovieId());
        verify(this.viewerRepository).findById(movieWatchDto.getViewerId());
        verify(this.movieWatchRepository).save(any(MovieWatch.class));
    }

    @Test
    void findMoviesWatchedByViewerId() {
        UUID viewerId = viewer.getId();
        List<MovieWatch> movieWatches = List.of(movieWatch);
        when(this.movieWatchRepository.findByViewerId(viewerId)).thenReturn(movieWatches);

        List<Movie> movies = this.movieWatchService.findMoviesWatchedByViewerId(viewerId);

        assertThat(movies).isNotEmpty();
        assertThat(movies).contains(movie);
        verify(this.movieWatchRepository).findByViewerId(viewerId);
    }

    @Test
    void findViewersByMovieId() {
        UUID movieId = movie.getId();
        List<MovieWatch> movieWatches = List.of(movieWatch);
        when(this.movieWatchRepository.findByMovieId(movieId)).thenReturn(movieWatches);

        List<Viewer> viewers = this.movieWatchService.findViewersByMovieId(movieId);

        assertThat(viewers).isNotEmpty();
        assertThat(viewers).contains(viewer);
        verify(this.movieWatchRepository).findByMovieId(movieId);
    }

    @Test
    void getTotalViews() {
        UUID movieId = movie.getId();
        when(this.movieWatchRepository.getTotalViews(movieId)).thenReturn(10);

        int totalViews = this.movieWatchService.getTotalViews(movieId);

        assertThat(totalViews).isEqualTo(10);
        verify(this.movieWatchRepository).getTotalViews(movieId);
    }

    @Test
    void getMoviesWatchedCount() {
        UUID viewerId = viewer.getId();
        when(this.movieWatchRepository.countMoviesWatchedByViewer(viewerId)).thenReturn(5);

        int moviesWatchedCount = this.movieWatchService.getMoviesWatchedCount(viewerId);

        assertThat(moviesWatchedCount).isEqualTo(5);
        verify(this.movieWatchRepository).countMoviesWatchedByViewer(viewerId);
    }

    @Test
    void convertToEntity() {
        when(this.movieRepository.findById(movieWatchDto.getMovieId())).thenReturn(Optional.of(movie));
        when(this.viewerRepository.findById(movieWatchDto.getViewerId())).thenReturn(Optional.of(viewer));

        MovieWatch converted = this.movieWatchService.convertToEntity(movieWatchDto);

        assertThat(converted).isNotNull();
        assertThat(converted.getMovie()).isEqualTo(movie);
        assertThat(converted.getViewer()).isEqualTo(viewer);
        verify(this.movieRepository).findById(movieWatchDto.getMovieId());
        verify(this.viewerRepository).findById(movieWatchDto.getViewerId());
    }

}
