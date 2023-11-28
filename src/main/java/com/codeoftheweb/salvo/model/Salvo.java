package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salvoes")
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @Column(name = "turnNumber")
    private Integer turnNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "salvo")
    private List<SalvoLocation> salvoLocations = new ArrayList<>();

    public Salvo(){
    }
    public Salvo(GamePlayer gamePlayer, Integer turnNumber){
        this.gamePlayer = gamePlayer;
        this.turnNumber = turnNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<SalvoLocation> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<SalvoLocation> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }
}
