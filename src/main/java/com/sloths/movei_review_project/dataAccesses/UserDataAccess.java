package com.sloths.movei_review_project.dataAccesses;

import com.sloths.movei_review_project.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataAccess extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
