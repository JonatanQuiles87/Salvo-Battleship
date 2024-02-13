package com.codeoftheweb.salvo.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SalvoDto {

    @NotNull
    private Integer turnNumber;

    @NotNull
    @NotEmpty
    private List<String> salvoLocations;

    public SalvoDto() {
    }

    public SalvoDto(@NotNull Integer turnNumber, @NotNull @NotEmpty List<String> salvoLocations) {
        this.turnNumber = turnNumber;
        this.salvoLocations = salvoLocations;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }
}
