package com.personalproject.moviecontrol.unit;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieViewRecord;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieViewRecordRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import com.personalproject.moviecontrol.services.ViewerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários da Classe - ViewerServiceTest")
public class ViewerServiceTest {

    @InjectMocks
    private ViewerService viewerService;

    @Mock
    private ViewerRepository viewerRepository;

    @Mock
    private MovieViewRecordRepository movieWatchRepository;

    private Viewer viewer;
    private ViewerDTO viewerDto;
    private Movie movie;
    private MovieViewRecord viewRecord;
    private UUID viewerId;

    @BeforeEach
    public void setUp() {
        UUID viewerId = UUID.randomUUID();
        viewer = Viewer.builder().id(viewerId).name("John").build();

        viewerDto = new ViewerDTO();
        viewerDto.setName("John");

        movie = Movie.builder().id(UUID.randomUUID()).title("Shrek").build();
        viewRecord = MovieViewRecord.builder().viewer(viewer).movie(movie).build();
    }

    @Test
    @DisplayName("DADO um Viewer preenchido " +
            "QUANDO realizada a ação de criar " +
            "ENTÃO deverá salvar o espectador com sucesso ")
    public void create() {
        when(this.viewerRepository.save(any(Viewer.class))).thenReturn(viewer);

        Viewer createdViewer = this.viewerService.create(viewerDto);

        assertNotNull(createdViewer);
        assertEquals(viewer.getId(), createdViewer.getId());
        assertEquals(viewer.getName(), createdViewer.getName());
        verify(this.viewerRepository, times(1)).save(any(Viewer.class));
    }

    @Test
    @DisplayName("DADO um Viewer " +
            "QUANDO chamada uma busca por ID " +
            "ENTÃO deverá retornar o espectador correspondente")
    public void findViewerById() {
        when(this.viewerRepository.findById(viewerId)).thenReturn(Optional.of(viewer));

        Optional<Viewer> result = this.viewerService.findViewerById(viewerId);

        assertTrue(result.isPresent());
        assertEquals(viewer.getId(), result.get().getId());
        assertEquals(viewer.getName(), result.get().getName());
        verify(this.viewerRepository, times(1)).findById(viewerId);
    }

    @Test
    @DisplayName("DADO uma busca por todos os viewers " +
            "QUANDO chamada a consulta " +
            "ENTÃO deverá retornar uma lista com todos os registros")
    public void findAllViewers() {
        Sort sort = Sort.by("name").ascending();

        when(this.viewerRepository.findAll(sort)).thenReturn(Arrays.asList(viewer));

        List<Viewer> result = this.viewerService.findAllViewers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(viewer.getId(), result.get(0).getId());
        assertEquals(viewer.getName(), result.get(0).getName());
        verify(this.viewerRepository, times(1)).findAll(sort);
    }

    @Test
    @DisplayName("DADO um ID de espectador " +
            "QUANDO chamada a ação de delete com esse ID " +
            "ENTÃO deverá remover o espectador correspondente ")
    public void delete() {
        doNothing().when(this.viewerRepository).deleteById(viewerId);

        this.viewerService.delete(viewerId);

        verify(this.viewerRepository, times(1)).deleteById(viewerId);
    }

    @Test
    @DisplayName("DADO um ViewerDTO " +
            "QUANDO chamada a ação para converter o DTO " +
            "ENTÃO deverá converter o DTO para uma entidade Viewer")
    public void convertToEntity() {
        Viewer result = this.viewerService.convertToEntity(viewerDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(viewer.getName());
    }
}