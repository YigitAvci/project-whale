package com.sloths.movie_review_project.entities;


import lombok.Getter;

import java.util.Date;

public class ActorDTO {

    @Getter
    private final long id;

    @Getter
    private final String fullName;

    @Getter
    private final String origin;

    @Getter
    private final Date birthdate;

    public ActorDTO(Actor actor){
        this.id = actor.getId();
        this.fullName = actor.getFullName();
        this.origin = actor.getOrigin();
        this.birthdate = actor.getBirthdate();;
    }

    @Override
    public String toString() {
        return "ActorDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", origin='" + origin + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
