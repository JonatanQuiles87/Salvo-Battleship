package com.codeoftheweb.salvo;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private Date date;

    public Game() {
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
