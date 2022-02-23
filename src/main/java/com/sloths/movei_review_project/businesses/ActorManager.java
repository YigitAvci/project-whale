package com.sloths.movei_review_project.businesses;

import com.sloths.movei_review_project.dataAccesses.ActorDataAccess;
import com.sloths.movei_review_project.entities.Actor;
import com.sloths.movei_review_project.entities.ActorDTO;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntityFail;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntitySuccess;
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
public class ActorManager {

    private final ActorDataAccess actorDataAccess;

    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @PostConstruct
    public void onInit() {
        session = entityManager.unwrap(Session.class);
    }

    @Autowired
    public ActorManager(ActorDataAccess actorDataAccess) {
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
        if(responseEntity.getData() != null) {
            System.out.println(responseEntity.getData().getMovies().size());
            return responseEntity;
        }
        return new CustomResponseEntityFail(String.format("the actor whose id is [%s] is not found", id));
    }

    public CustomResponseEntity findActorByFullName(String fullName) {
        List<Actor> actorList = actorDataAccess.findActorsByFullName(fullName);
        List<ActorDTO> actorDTOList = actorList.stream().map(ActorDTO::new).collect(Collectors.toList());
        CustomResponseEntitySuccess<List<ActorDTO>> responseEntity = new CustomResponseEntitySuccess<>(actorDTOList, String.format("the actor whose full name is [%s] is found", fullName));
        if(responseEntity.getData().size() > 0) {
            return responseEntity;
        }
        return new CustomResponseEntityFail(String.format("the actor whose full name is [%s] is not found", fullName));
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
