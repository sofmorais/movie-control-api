package com.personalproject.moviecontrol.controllers;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.services.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/viewer")
public class ViewerController {

    @Autowired
    private ViewerService viewerService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ViewerDTO viewer) {
        this.viewerService.create(viewer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{viewerId}")
    public ResponseEntity<Optional<Viewer>> findById(@PathVariable UUID viewerId) {
        return ResponseEntity.ok(this.viewerService.findViewerById(viewerId));
    }

    @GetMapping
    public ResponseEntity<List<Viewer>> findAll() {
        return ResponseEntity.ok(this.viewerService.findAllViewers());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(UUID viewerId) {
        this.viewerService.delete(viewerId);
        return ResponseEntity.noContent().build();
    }
}
