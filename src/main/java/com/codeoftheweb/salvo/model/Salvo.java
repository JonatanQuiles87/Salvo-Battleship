package com.codeoftheweb.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salvoes")
@Getter
@Setter
@NoArgsConstructor

public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @Column(name = "turnNumber")
    private Integer turnNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "salvo")
    private Set<SalvoLocation> salvoLocations = new HashSet<>();

    public Salvo(GamePlayer gamePlayer, Integer turnNumber){
        this.gamePlayer = gamePlayer;
        this.turnNumber = turnNumber;
    }
}
