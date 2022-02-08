package com.sloths.movei_review_project.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "movies")
@Validated
public class Movie {

    @NotNull(message = "id field cannot be assigned to null")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @NotBlank(message = "name field for movie is mandatory")
    @Column(name = "name") //This @Column annotation is optional. Without this annotation, jpa will create the table using name of variable for related column
    @Getter
    @Setter
    private String name;

    //private List<String> cast; //Later add this statement properly

    @Column(name = "release_date")
    @Getter
    @Setter
    private Date releaseDate;

    // Column annotation is not necessary unless you want to name the column different name for this field
    @Getter
    @Setter
    private double rate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "movies_actors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @Getter
    @Setter
    private List<Actor> cast;

    public Movie(int id, String name, Date releaseDate, double rate) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rate = rate;
    }

    public void setNameIfNotNull(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setReleaseDateIfNotNull(Date releaseDate) {
        if(releaseDate!= null) {
            this.releaseDate = releaseDate;
        }
    }

    public void setRateIfNotNull(double rate) {
        if(rate != 0.0) {
            this.rate = rate;
        }
    }

    public void setFieldsIfNotNull(Movie movie) {
        setNameIfNotNull(movie.getName());
        setReleaseDateIfNotNull(movie.getReleaseDate());
        setRateIfNotNull(movie.getRate());
    }

    public void addActorToCast(Actor actor) {
        this.cast.add(actor);
    }



    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", rate=" + rate +
                ", cast=" + cast +
                '}';
    }
}
