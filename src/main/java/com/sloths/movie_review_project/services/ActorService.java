package com.sloths.movie_review_project.services;

import com.sloths.movie_review_project.repositories.ActorRepository;
import com.sloths.movie_review_project.entities.Actor;
import com.sloths.movie_review_project.entities.ActorDTO;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movie_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActorService {

    private final ActorRepository actorDataAccess;

    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @PostConstruct
    public void onInit() {
        session = entityManager.unwrap(Session.class);
    }

    @Autowired
    public ActorService(ActorRepository actorDataAccess) {
        this.actorDataAccess = actorDataAccess;
    }

    public CustomResponseEntity add(Actor actor){
        actorDataAccess.save(actor);
        return new CustomResponseEntitySuccess<>(actor, String.format("the actor [%s] is added successfully", actor));
    }

    public Actor getActorById(long id) {
        return actorDataAccess.getById(id);
    }

    public Boolean isExist(long id) {
        return actorDataAccess.existsById(id);
    }

    public CustomResponseEntity findActorById(long id) {
        CustomResponseEntitySuccess<Actor> responseEntity = new CustomResponseEntitySuccess<>(actorDataAccess.findActorById(id), String.format("the actor whose id is [%s] is found", id));
        if(responseEntity.getData() == null) {
            return new CustomResponseEntityFail(String.format("the actor whose id is [%s] is not found", id));
        }
        log.info("movie list size is [{}]", responseEntity.getData().getMovies().size());
        return responseEntity;
    }

    public CustomResponseEntity findActorByFullName(String fullName) {
        List<Actor> actorList = actorDataAccess.findActorsByFullName(fullName);
        List<ActorDTO> actorDTOList = actorList.stream().map(ActorDTO::new).collect(Collectors.toList());
        CustomResponseEntitySuccess<List<ActorDTO>> responseEntity = new CustomResponseEntitySuccess<>(actorDTOList, String.format("the actor whose full name is [%s] is found", fullName));
        if(responseEntity.getData().size() <= 0) {
            return new CustomResponseEntityFail(String.format("the actor whose full name is [%s] is not found", fullName));
        }
        return responseEntity;
    }

    public List<Actor> findActorByFullNameViaCriteria(String fullName) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Actor> actorQuery = criteriaBuilder.createQuery(Actor.class);
        Root<Actor> root = actorQuery.from(Actor.class);
        actorQuery.select(root); //in this example we have only one root, so this statement is unnecessary
        actorQuery.where(criteriaBuilder.equal(root.get("fullName"), fullName));
        return session.createQuery(actorQuery).getResultList();
    }


    public List<ActorDTO> experimentalQueryFunction(String movieName) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Actor> actorQuery = criteriaBuilder.createQuery(Actor.class);
        Root<Actor> root = actorQuery.from(Actor.class);
        actorQuery.select(root); //in this example we have only one root, so this statement is unnecessary
        actorQuery.where(criteriaBuilder.equal(root.get("movies").get("name"), movieName));
        List<Actor> resultList = session.createQuery(actorQuery).getResultList();
        return resultList.stream().map(ActorDTO::new).collect(Collectors.toList());
    }

}
