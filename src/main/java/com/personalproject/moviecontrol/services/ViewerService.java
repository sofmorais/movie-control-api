package com.personalproject.moviecontrol.services;

import com.personalproject.moviecontrol.dtos.ViewerDTO;
import com.personalproject.moviecontrol.models.Viewer;
import com.personalproject.moviecontrol.repositories.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ViewerService {

    @Autowired
    private ViewerRepository viewerRepository;

    public Viewer create(ViewerDTO viewerDto) {
        Viewer viewer = this.convertToEntity(viewerDto);
        return this.viewerRepository.save(viewer);
    }

    public Optional<Viewer> findViewerById(UUID id) {
        return this.viewerRepository.findById(id);
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
