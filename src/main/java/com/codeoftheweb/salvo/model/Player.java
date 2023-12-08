package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "player")
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "player")
    private List<Score> scores = new ArrayList<>();

    public Player() {

    }

    public Player(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public Long getId (){
        return id;
    }
    public void setId (long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
