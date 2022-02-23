package com.sloths.movei_review_project.dataAccesses;

import com.sloths.movei_review_project.entities.Actor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorDataAccess extends JpaRepository<Actor, Long> {

    @Query(value = "select * from actors as a where a.id=:id", nativeQuery = true)
    Actor findActorById(@Param("id") long id);

    @Query(value = "select * from actors as a where a.full_name=:fullName", nativeQuery = true)
    List<Actor> findActorsByFullName(@Param("fullName") String fullName);

    Boolean existsById(long id);
}
