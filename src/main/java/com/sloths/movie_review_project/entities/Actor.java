package com.sloths.movie_review_project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actors")
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private String origin;

    private Date birthdate;

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToMany(mappedBy = "cast")
    private List<Movie> movies;

    public Actor(Actor actor) {
        this.setId(actor.getId());
        this.setFullName(actor.getFullName());
        this.setMovies(actor.getMovies());
        this.setBirthdate(actor.getBirthdate());
        this.setOrigin(actor.getOrigin());

    }

    public void setFullNameIfNotNull(String fullName) {
        if(fullName != null && !fullName.isEmpty()) {
            this.setFullName(fullName);
        }
    }

    public void setOriginIfNotNull(String origin) {
        if(origin != null && !origin.isEmpty()) {
            this.setOrigin(origin);
        }
    }

    public void setBirthdateIfNotNull(Date birthdate) {
        if(birthdate != null) {
            this.setBirthdate(birthdate);
        }
    }

    public void setMoviesIfNotNull(List<Movie> movies) {
        if(movies != null && movies.size() > 0) {
            movies.stream().filter(movie -> movie.getId() == 0).forEach(movie -> this.getMovies().add(movie));
        }
    }

    public void setFieldsIfNotNull(Actor actor) {
        setFullNameIfNotNull(actor.getFullName());
        setOriginIfNotNull(actor.getOrigin());
        setBirthdateIfNotNull(actor.getBirthdate());
        setMoviesIfNotNull(actor.getMovies());
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", origin='" + origin + '\'' +
                ", birthdate=" + birthdate +
                ", movies=" + movies +
                '}';
    }
}
