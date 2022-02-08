package com.sloths.movei_review_project.dataAccesses;

import com.sloths.movei_review_project.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDataAccess extends JpaRepository<Movie, Integer> {
}
