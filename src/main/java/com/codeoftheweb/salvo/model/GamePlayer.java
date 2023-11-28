package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "join_date")
    private Date joinDate;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @OneToMany(mappedBy = "gamePlayer")
    Set<Ship> ships = new HashSet<>();

    public GamePlayer(){
    }
    public GamePlayer(Game game, Player player, Date joinDate) {
        this.joinDate = joinDate;
        this.game= game;
        this.player= player;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoinDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(joinDate);
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPlayerEmail(){
        return player.getEmail();
    }

    public Player getPlayer() {
        return player;
    }
    public Game getGame() {
        return game;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }
}
