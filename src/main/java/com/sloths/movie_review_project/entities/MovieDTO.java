package com.sloths.movie_review_project.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private long id;
    private String name;
    private Date releaseDate;
    private double rate;
    private List<ActorDTO> cast;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.releaseDate = movie.getReleaseDate();
        this.rate = movie.getRate();
    }

    public MovieDTO(Movie movie, List<ActorDTO> actorDTOS) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.releaseDate = movie.getReleaseDate();
        this.rate = movie.getRate();
        this.cast = actorDTOS;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", rate=" + rate +
                ", cast=" + cast +
                '}';
    }
}
