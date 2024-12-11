package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.services.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria um novo filme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MovieDTO movie) {
        this.movieService.create(movie);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna filme por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme encontrado"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @GetMapping(value = "/{movieId}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable UUID movieId) {
        return ResponseEntity.ok(this.movieService.findMovieById(movieId));
    }

    @Operation(summary = "Recuperar a lista de todos os filmes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.ok(this.movieService.findAllMovies());
    }

    @Operation(summary = "Excluir um filme pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado com o ID fornecido")
    })
    @DeleteMapping(value = "/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable UUID movieId) {
        this.movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }

}
