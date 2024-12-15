package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Controle de Filmes", description = "Endpoints para gerenciar os filmes")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Cria um novo filme",
            description = "Esse método cria um novo filme. Envie um objeto `MovieDTO` válido no corpo da requisição (JSON) para criar o filme.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid MovieDTO movie) {
        this.movieService.create(movie);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna um filme por ID",
            description = "Esse método retorna um filme utilizando o `movieId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados"),
    })
    @GetMapping(value = "/{movieId}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable UUID movieId) {
        return ResponseEntity.ok(this.movieService.findMovieById(movieId));
    }

    @Operation(summary = "Retorna todos os filmes cadastrados",
            description = "Esse método recupera uma lista com todos os filmes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(this.movieService.findAllMovies());
    }

    @Operation(summary = "Retorna todos os filmes disponíveis",
            description = "Esse método recupera uma lista com todos os filmes com status disponível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/availables")
    public ResponseEntity<List<Movie>> getMoviesAvailable() {
        return ResponseEntity.ok(this.movieService.getMoviesAvailable());
    }

    @Operation(summary = "Retorna todos os filmes indisponíveis",
            description = "Esse método recupera uma lista com todos os filmes com status indisponível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/unavailables")
    public ResponseEntity<List<Movie>> getMoviesUnavailable() {
        return ResponseEntity.ok(this.movieService.getMoviesUnavailable());
    }

    @Operation(summary = "Marca um filme como indisponível",
            description = "Esse método marca um filme como indisponível utilizando o `movieId` como parâmetro.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @PutMapping(value = "/{movieId}")
    public ResponseEntity<Void> markAsUnavailable(@PathVariable UUID movieId) {
        this.movieService.markAsUnavailable(movieId);
        return ResponseEntity.noContent().build();
    }

}
