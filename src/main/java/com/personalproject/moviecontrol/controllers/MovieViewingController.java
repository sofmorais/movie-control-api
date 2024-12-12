package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.MovieViewRecordDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.services.MovieWatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movie-viewing")
@Tag(name = "Controle de Visualizações de Filmes", description = "Endpoints para gerenciar as visualizações")
public class MovieViewingController {

    @Autowired
    private MovieWatchService movieWatchService;

    @Operation(summary = "Cria uma nova visualização de filme",
            description = "Esse método cria uma nova visualização de filme. Envie um objeto `MovieViewRecordDTO` válido no corpo da requisição (JSON) para registrar a visualização.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody MovieViewRecordDTO viewRecordDto) {
        this.movieWatchService.create(viewRecordDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna os filmes assistidos por um espectador",
            description = "Esse método retorna uma lista com os filmes assistidos por um espectador utilizando o `viewerId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Espectador não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping(value = "/{viewerId}/movies")
    public ResponseEntity<List<Movie>> findMoviesByViewerId(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.movieWatchService.findMoviesWatchedByViewerId(viewerId));
    }

    @Operation(summary = "Retorna os espectadores que assistiram a um filme",
            description = "Esse método retorna uma lista com todos os espectadores que assistiram a um filme utilizando o `movieId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping(value = "/{movieId}/viewers")
    public ResponseEntity<List<Viewer>> findViewersByMovieId(@PathVariable UUID movieId) {
        return ResponseEntity.ok(this.movieWatchService.findViewersByMovieId(movieId));
    }

    @Operation(summary = "Retorna o total de visualizações de um filme",
            description = "Esse método retorna o total de visualizações de um filme utilizando o `movieId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/movie/{movieId}/total-views")
    public ResponseEntity<Integer> getTotalViews(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieWatchService.getTotalViews(movieId));
    }

    @Operation(summary = "Retorna o total de filmes assistidos por um espectador",
            description = "Esse método retorna o total de filmes assistidos por um espectador utilizando o `viewerId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Espectador não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/viewer/{viewerId}/total-movies-watched")
    public ResponseEntity<Integer> getMoviesWatchedCount(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.movieWatchService.getMoviesWatchedCount(viewerId));
    }
}
