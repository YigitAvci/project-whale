package com.sloths.movei_review_project.controllers;

import com.sloths.movei_review_project.businesses.ActorManager;
import com.sloths.movei_review_project.entities.Actor;
import com.sloths.movei_review_project.entities.ActorDTO;
import com.sloths.movei_review_project.helpers.responseHelpers.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorManager actorManager;

    @Autowired
    public ActorController(ActorManager actorManager) {
        this.actorManager = actorManager;
    }

    @PostMapping("/addActor")
    public CustomResponseEntity add(@RequestBody Actor actor) {
        return actorManager.add(actor);
    }

    @GetMapping("/findActorById")
    public CustomResponseEntity findById(@RequestParam(value = "id") int id) {
        return actorManager.findActorById(id);
    }

    @GetMapping("/findActorByFullName")
    public CustomResponseEntity findByFullName(@RequestParam(value = "fullName") String fullName) {
        return actorManager.findActorByFullName(fullName);
    }

    @GetMapping("/findActorsByFullNameViaCriteria")
    public List<Actor> findActorByFullNameViaCriteria(@RequestParam(value = "fullName") String fullName) {
        return actorManager.findActorByFullNameViaCriteria(fullName);
    }

    @GetMapping("/findActorsByMovieName")
    public List<ActorDTO> findActorsByMovieName(@RequestParam(value = "movieName") String movieName) {
        return actorManager.experimentalQueryFunction(movieName);
    }


}
