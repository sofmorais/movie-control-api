package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.services.ViewerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/viewer")
@Tag(name = "Controle de Espectadores", description = "Endpoints para gerenciar os espectadores")
public class ViewerController {

    @Autowired
    private ViewerService viewerService;

    @Operation(summary = "Cria um novo espectador",
            description = "Esse método cria um novo espectador. Envie um objeto `ViewerDTO` válido no corpo da requisição (JSON) para criar o espectador.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody ViewerDTO viewer) {
        this.viewerService.create(viewer);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna um espectador por ID",
            description = "Esse método retorna um espectador utilizando o `viewerId` como parâmetro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Espectador não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados"),
    })
    @GetMapping(value = "/{viewerId}")
    public ResponseEntity<Optional<Viewer>> findById(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.viewerService.findViewerById(viewerId));
    }

    @Operation(summary = "Retorna todos os espectadores cadastrados",
            description = "Esse método recupera uma lista com todos os espectadores", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping
    public ResponseEntity<List<Viewer>> findAll() {
        return ResponseEntity.ok(this.viewerService.findAllViewers());
    }

    @Operation(summary = "Retorna todos os espectadores ativos",
            description = "Esse método recupera uma lista com todos os espectadores com status ativo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/actives")
    public ResponseEntity<List<Viewer>> getEspectadoresAtivos() {
        return ResponseEntity.ok(this.viewerService.getEspectadoresAtivos());
    }

    @Operation(summary = "Retorna todos os espectadores inativos",
            description = "Esse método recupera uma lista com todos os espectadores com status inativos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    @GetMapping("/inactives")
    public ResponseEntity<List<Viewer>> getEspectadoresInativos() {
        return ResponseEntity.ok(this.viewerService.getEspectadoresInativos());
    }

    @Operation(summary = "Marca um espectador como inativo",
            description = "Esse método marca um espectador como inativo utilizando o `viewerId` como parâmetro.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Espectador não encontrado")
    })
    @PutMapping(value = "/{viewerId}")
    public ResponseEntity<Void> markAsInactive(@PathVariable UUID viewerId) {
        this.viewerService.markAsUnavailable(viewerId);
        return ResponseEntity.noContent().build();
    }
}
