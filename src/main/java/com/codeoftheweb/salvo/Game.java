package com.codeoftheweb.salvo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name ="native", strategy = "native")
    private long id;
    private String dateInitial;

    public Game(){
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateInitial() {
        Date dateNoFormat = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat changeFormat = new SimpleDateFormat(strDateFormat);
        return changeFormat.format(dateNoFormat);
    }

    public void setDateInitial(LocalDateTime dateInitial) {
    }

    public void setDateInitial(String dateInitial) {
        this.dateInitial = dateInitial;
    }
}
