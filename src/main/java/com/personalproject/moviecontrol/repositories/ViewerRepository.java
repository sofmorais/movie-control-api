package com.personalproject.moviecontrol.repositories;

import com.personalproject.moviecontrol.models.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ViewerRepository extends JpaRepository<Viewer, UUID> {
}
