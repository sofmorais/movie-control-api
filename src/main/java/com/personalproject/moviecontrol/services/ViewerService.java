package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.models.MovieWatch;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import com.personalproject.moviecontrol.repositories.MovieWatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ViewerService {

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private MovieWatchRepository movieWatchRepository;

    public Viewer create(ViewerDTO viewerDto) {
        Viewer viewer = this.convertToEntity(viewerDto);
        return this.viewerRepository.save(viewer);
    }

    public Optional<Viewer> findViewerById(UUID id) {
        return this.viewerRepository.findById(id);
    }

    public List<Movie> findWatchedMoviesByViewer(UUID viewerId) {
        return movieWatchRepository.findByViewerId(viewerId).stream()
                .map(MovieWatch::getMovie)
                .collect(Collectors.toList());
    }

    public List<Viewer> findAllViewers() {
        return this.viewerRepository.findAll();
    }

    public Viewer convertToEntity(ViewerDTO viewerDto) {
        return Viewer.builder()
                .id(viewerDto.getId())
                .name(viewerDto.getName())
                .build();
    }
}
