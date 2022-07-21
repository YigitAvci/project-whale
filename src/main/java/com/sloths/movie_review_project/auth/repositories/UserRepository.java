package com.sloths.movie_review_project.auth.repositories;

import com.sloths.movie_review_project.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
