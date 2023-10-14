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
    private Date creationDate;

    public Game() {
        this.creationDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
