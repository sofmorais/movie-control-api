package com.personalproject.moviecontrol.unit;

import com.personalproject.moviecontrol.dtos.MovieViewRecordDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieViewRecord;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.repositories.MovieViewRecordRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import com.personalproject.moviecontrol.services.MovieViewingService;
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
@DisplayName("Testes Unitários da Classe - MovieViewingServiceTest")
class MovieViewingServiceTest {

    @Mock
    private MovieViewRecordRepository viewRecordRepository;

    @Mock
    private ViewerRepository viewerRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieViewingService movieViewingService;

    private Movie movie;
    private Viewer viewer;
    private MovieViewRecord viewRecord;
    private MovieViewRecordDTO viewRecordDto;

    @BeforeEach
    public void setUp() {
        movie = new Movie();
        movie.setId(UUID.randomUUID());

        viewer = new Viewer();
        viewer.setId(UUID.randomUUID());

        viewRecord = MovieViewRecord.builder()
                .movie(movie)
                .viewer(viewer)
                .viewingDate(new Date())
                .build();

        viewRecordDto = new MovieViewRecordDTO();
        viewRecordDto.setMovieId(movie.getId());
        viewRecordDto.setViewerId(viewer.getId());
    }

    @Test
    @DisplayName("DADO um MovieViewRecord preenchido " +
            "QUANDO chamada a ação de criar " +
            "ENTÃO deverá salvar o registro de visualização com sucesso")
    public void create() {
        when(this.movieRepository.findById(viewRecordDto.getMovieId())).thenReturn(Optional.of(movie));
        when(this.viewerRepository.findById(viewRecordDto.getViewerId())).thenReturn(Optional.of(viewer));
        when(this.viewRecordRepository.save(any(MovieViewRecord.class))).thenReturn(viewRecord);

        MovieViewRecord result = this.movieViewingService.create(viewRecordDto);

        assertThat(result).isNotNull();
        assertThat(result.getMovie()).isEqualTo(movie);
        assertThat(result.getViewer()).isEqualTo(viewer);
        verify(this.movieRepository).findById(viewRecordDto.getMovieId());
        verify(this.viewerRepository).findById(viewRecordDto.getViewerId());
        verify(this.viewRecordRepository).save(any(MovieViewRecord.class));
    }

    @Test
    @DisplayName("DADO um Viewer " +
            "QUANDO chamada a busca por filmes assistidos pelo ID do espectador " +
            "ENTÃO deverá retornar a lista com todos os filmes assistidos")
    public void findMoviesWatchedByViewerId() {
        UUID viewerId = viewer.getId();
        List<MovieViewRecord> viewRecordList = List.of(viewRecord);
        when(this.viewRecordRepository.findByViewerId(viewerId)).thenReturn(viewRecordList);

        List<Movie> result = this.movieViewingService.findMoviesWatchedByViewerId(viewerId);

        assertThat(result).isNotEmpty();
        assertThat(result).contains(movie);
        verify(this.viewRecordRepository).findByViewerId(viewerId);
    }

    @Test
    @DisplayName("DADO um Movie " +
            "QUANDO chamada a busca por espectadores do filme " +
            "ENTÃO deverá retornar a lista de espectadores que o assistiram")
    public void findViewersByMovieId() {
        UUID movieId = movie.getId();
        List<MovieViewRecord> movieWatches = List.of(viewRecord);
        when(this.viewRecordRepository.findByMovieId(movieId)).thenReturn(movieWatches);

        List<Viewer> result = this.movieViewingService.findViewersByMovieId(movieId);

        assertThat(result).isNotEmpty();
        assertThat(result).contains(viewer);
        verify(this.viewRecordRepository).findByMovieId(movieId);
    }

    @Test
    @DisplayName("DADO um Movie " +
            "QUANDO realizada a busca pelo total de visualizações " +
            "ENTÃO deverá retornar o número total de visualizações")
    public void getTotalViews() {
        UUID movieId = movie.getId();
        when(this.viewRecordRepository.getTotalViews(movieId)).thenReturn(10);

        int totalViews = this.movieViewingService.getTotalViews(movieId);

        assertThat(totalViews).isEqualTo(10);
        verify(this.viewRecordRepository).getTotalViews(movieId);
    }

    @Test
    @DisplayName("DADO um Viewer " +
            "QUANDO chamada a consulta de quantidade de filmes assistidos " +
            "ENTÃO deverá retornar a quantidade total de filmes assistidos")
    public void getMoviesWatchedCount() {
        UUID viewerId = viewer.getId();
        when(this.viewRecordRepository.countMoviesWatchedByViewer(viewerId)).thenReturn(5);

        int moviesWatchedCount = this.movieViewingService.getMoviesWatchedCount(viewerId);

        assertThat(moviesWatchedCount).isEqualTo(5);
        verify(this.viewRecordRepository).countMoviesWatchedByViewer(viewerId);
    }

    @Test
    @DisplayName("DADO um MovieViewRecordDTO " +
            "QUANDO chamada a ação para converter o DTO " +
            "ENTÃO deverá converter o DTO para uma entidade MovieViewRecordDTO")
    public void convertToEntity() {
        when(this.movieRepository.findById(viewRecordDto.getMovieId())).thenReturn(Optional.of(movie));
        when(this.viewerRepository.findById(viewRecordDto.getViewerId())).thenReturn(Optional.of(viewer));

        MovieViewRecord result = this.movieViewingService.convertToEntity(viewRecordDto);

        assertThat(result).isNotNull();
        assertThat(result.getMovie()).isEqualTo(movie);
        assertThat(result.getViewer()).isEqualTo(viewer);
        verify(this.movieRepository).findById(viewRecordDto.getMovieId());
        verify(this.viewerRepository).findById(viewRecordDto.getViewerId());
    }

}
