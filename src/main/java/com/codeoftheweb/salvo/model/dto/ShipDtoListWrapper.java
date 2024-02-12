package com.codeoftheweb.salvo.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ShipDtoListWrapper {

    @Valid
    @NotNull
    @NotEmpty
    private List<ShipDto> ships;

    public ShipDtoListWrapper() {
    }

    public ShipDtoListWrapper(@Valid @NotNull @NotEmpty List<ShipDto> ships) {
        this.ships = ships;
    }

    public List<ShipDto> getShips() {
        return ships;
    }

    public void setShips(List<ShipDto> ships) {
        this.ships = ships;
    }
}
