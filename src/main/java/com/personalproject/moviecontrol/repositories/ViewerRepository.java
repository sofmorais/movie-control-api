package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, UUID> {
}
