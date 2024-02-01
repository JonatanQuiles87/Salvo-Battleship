package com.codeoftheweb.salvo.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Score> scores = new ArrayList<>();

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }


    public String getCreationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(creationDate);
    }

}
