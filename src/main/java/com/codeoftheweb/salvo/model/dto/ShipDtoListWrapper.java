package com.codeoftheweb.salvo.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ShipDtoListWrapper {

    @Valid
    @NotNull
    @NotEmpty
    private List<ShipDto> shipDtoList;

    public ShipDtoListWrapper() {
    }

    public ShipDtoListWrapper(@Valid @NotNull @NotEmpty List<ShipDto> shipDtoList) {
        this.shipDtoList = shipDtoList;
    }

    public List<ShipDto> getShipDtoList() {
        return shipDtoList;
    }

    public void setShipDtoList(List<ShipDto> shipDtoList) {
        this.shipDtoList = shipDtoList;
    }
}
