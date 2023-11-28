package com.codeoftheweb.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "salvo_locations")
public class SalvoLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column(name= "grid_cell")
    private String gridCell;

    @ManyToOne
    @JoinColumn(name = "salvo_id")
    private Salvo salvo;

    public SalvoLocation(){
    }

    public SalvoLocation(Salvo salvo, String gridCell){
        this.salvo = salvo;
        this.gridCell = gridCell;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Salvo getSalvo() {
        return salvo;
    }

    public void setSalvo(Salvo salvo) {
        this.salvo = salvo;
    }

    public String getGridCell() {
        return gridCell;
    }

    public void setGridCell(String gridCell) {
        this.gridCell = gridCell;
    }
}
