package com.codeoftheweb.salvo.model;

import com.codeoftheweb.salvo.model.GamePlayer;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name = "shipLocation")
    private List<String> shipLocation = new ArrayList<>();

    private String shipType;

    public Ship(){
    }

    public Ship(String type, GamePlayer gamePlayer, List<String> shipLocation){
        this.shipType = type;
        this.gamePlayer = gamePlayer;
        this.shipLocation = shipLocation;
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

    public List<String> getShipLocation() {
        return shipLocation;
    }

    public String getShipType() {
        return shipType;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocation(List<String> shipLocation) {
        this.shipLocation = shipLocation;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
}
