package com.codeoftheweb.salvo.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ShipDto {

    @NotNull
    @NotEmpty
    private String shipType;

    @NotNull
    @NotEmpty
    private List<String> shipLocations;

    public ShipDto() {
    }

    public ShipDto(@NotNull @NotEmpty String shipType, @NotNull @NotEmpty List<String> shipLocations) {
        this.shipType = shipType;
        this.shipLocations = shipLocations;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public List<String> getShipLocations() {
        return shipLocations;
    }

    public void setShipLocations(List<String> shipLocations) {
        this.shipLocations = shipLocations;
    }
}
