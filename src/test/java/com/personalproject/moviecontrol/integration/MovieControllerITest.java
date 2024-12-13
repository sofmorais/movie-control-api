package com.personalproject.moviecontrol.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalproject.moviecontrol.builders.MovieBuilder;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.repositories.MovieRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Testes de Integração da Classe - MovieControllerITest")
public class MovieControllerITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    static String URL_BASE_PATH = "/api/v1/movies";

    @Autowired
    private MovieRepository movieRepository;

    static MovieBuilder movieBuilder;

    @BeforeAll
    static void initBeforeClass() {
        movieBuilder = new MovieBuilder();
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("DADO uma requisição ao endpoint para criar um filme " +
            "QUANDO chamada a ação " +
            "ENTÃO deverá ealizar a ação com sucesso")
    public void create() throws Exception {
        Movie movie = movieBuilder.create();

        MvcResult mvcResult = mockMvc.perform(post(URL_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isNoContent())
                .andReturn();

        Assertions.assertNotNull(mvcResult);
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

}
