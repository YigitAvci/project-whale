package com.sloths.movie_review_project.repositories;

import com.sloths.movie_review_project.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value = "select * from actors as a where a.id=:id", nativeQuery = true)
    Actor findActorById(@Param("id") long id);

    @Query(value = "select * from actors as a where a.full_name=:fullName", nativeQuery = true)
    List<Actor> findActorsByFullName(@Param("fullName") String fullName);

    Boolean existsById(long id);
}
