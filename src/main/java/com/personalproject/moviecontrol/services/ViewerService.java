package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Movie;
import com.personalproject.moviecontrol.models.MovieViewRecord;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.MovieViewRecordRepository;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private MovieViewRecordRepository movieViewRecordRepository;

    public Viewer create(ViewerDTO viewerDto) {
        Viewer viewer = this.convertToEntity(viewerDto);
        return this.viewerRepository.save(viewer);
    }

    public Optional<Viewer> findViewerById(UUID id) {
        return this.viewerRepository.findById(id);
    }

    public List<Movie> findWatchedMoviesByViewer(UUID viewerId) {
        return movieViewRecordRepository.findByViewerId(viewerId).stream()
                .map(MovieViewRecord::getMovie)
                .collect(Collectors.toList());
    }

    public List<Viewer> findAllViewers() {
        Sort sort = Sort.by("name").ascending();
        return this.viewerRepository.findAll(sort);
    }

    public void delete(UUID viewerId) {
        this.viewerRepository.deleteById(viewerId);
    }

    public Viewer convertToEntity(ViewerDTO viewerDto) {
        return Viewer.builder()
                .name(viewerDto.getName())
                .build();
    }
}
