package com.example.TimeTracker.repo;

import com.example.TimeTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
    // You can add custom query methods here if needed
}
