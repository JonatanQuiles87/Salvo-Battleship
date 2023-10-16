package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    public Player() {
    }
    public Player (String userName) {
        this.userName = userName;
    }
    public long getId (){
        return id;
    }
    public void setId (long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName (String userName) {
        this.userName = userName;
    }
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers;
}
