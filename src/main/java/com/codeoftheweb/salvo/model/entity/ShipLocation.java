package com.codeoftheweb.salvo.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "ship_locations")
public class ShipLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;
    @Column(name = "grid_cell")
    private String gridCell;

    public ShipLocation(){
    }

    public ShipLocation(Ship ship, String gridCell){
        this.ship = ship;
        this.gridCell = gridCell;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getGridCell() {
        return gridCell;
    }

    public void setGridCell(String gridCell) {
        this.gridCell = gridCell;
    }
}