package com.personalproject.moviecontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalproject.moviecontrol.controllers.MovieController;
import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import com.personalproject.moviecontrol.services.MovieService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Testes de Integração da Classe - MovieViewControllerITest")
public class MovieControllerITest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    static String URL_BASE_PATH = "/api/v1/movies";

    static MovieDTO movieDto;
    private Movie movie;

    @BeforeAll
    static void initBeforeClass() {
        movieDto = new MovieDTO();
        movieDto.setTitle("Inception");
        movieDto.setGenre("Sci-Fi");
        movieDto.setReleaseYear(2010);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = this.movieService.convertToEntity(movieDto);
    }

    @Test
    public void create() throws Exception {
        when(movieService.create(any(MovieDTO.class))).thenReturn(movie);

        MvcResult mvcResult = mockMvc.perform(post(URL_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieDto)))
                        .andExpect(status().isNoContent())
                        .andReturn();

        verify(movieService, times(1)).create(any(MovieDTO.class));
        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.NO_CONTENT.value());
    }

}
