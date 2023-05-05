package com.sloths.movie_review_project.auth.repositories;

import com.sloths.movie_review_project.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
