package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "game_players")
@Getter
@Setter
@NoArgsConstructor

public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Set<Ship> ships = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "gamePlayer")
    private Set<Salvo> salvoes = new HashSet<>();

    public GamePlayer(Game game, Player player, Date joinDate) {
        this.joinDate = joinDate;
        this.game= game;
        this.player= player;
    }

    public String getJoinDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(joinDate);
    }

}
