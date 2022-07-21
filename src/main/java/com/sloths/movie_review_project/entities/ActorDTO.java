package com.sloths.movie_review_project.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ActorDTO {

    private final long id;

    private final String fullName;

    private final String origin;

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
