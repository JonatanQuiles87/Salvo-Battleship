package com.codeoftheweb.salvo.model.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "scoreNumber")
    private Double scoreNumber;

    @Column(name = "finish_date")
    private Date finishDate;

    public Score(){}

    public Score (Game game, Player player, Double scoreNumber, Date finishDate) {
        this.game = game;
        this.player = player;
        this.scoreNumber = scoreNumber;
        this.finishDate = finishDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Double getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(Double scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public String getFinishDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(finishDate);
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
