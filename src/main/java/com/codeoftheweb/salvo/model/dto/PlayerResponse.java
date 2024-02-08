package com.codeoftheweb.salvo.model.dto;

public class PlayerResponse {

    private String username;

    public PlayerResponse() {
    }

    public PlayerResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
