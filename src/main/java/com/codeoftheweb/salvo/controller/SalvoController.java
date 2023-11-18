package com.codeoftheweb.salvo.controller;


import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @RequestMapping("/games")
    private List<Object> getGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(this::makeGameDTO)
                .collect(Collectors.toList());
    }
    @RequestMapping("/game_view/{gamePlayerId}")
    private Map<String, Object> getGameView(@PathVariable long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).orElse(null);

        if (gamePlayer == null) {
            // Handle the case where the GamePlayer is not found, e.g., return an error response.
            // You can customize this based on your requirements.
            return Collections.singletonMap("error", "GamePlayer not found");
        }

        return makeGameViewDTO(gamePlayer);
    }


    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        // CREATE A LIST of maps WITH GAME PLAYERS
        List<Object> gamePlayersDTO = game.getGamePlayers()
                .stream()
                .sorted(Comparator.comparing(GamePlayer::getId))
                .map(this::makeGamePlayerDTO)
                .collect(Collectors.toList());
        dto.put("gamePlayers", gamePlayersDTO);
        return dto;
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayersDTO(gamePlayer.getPlayer()));

        return dto;
    }


    private Map<String, Object> makePlayersDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("email", player.getEmail());
        return dto;


    }


    public Map<String, Object> makeShipsDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("type", ship.getShipType());
        dto.put("locations", ship.getShipLocation());
        return dto;
    }
    private List<String> getShipTypes(GamePlayer gamePlayer) {
        return gamePlayer.getShips()
                .stream()
                .map(Ship::getShipType)
                .collect(Collectors.toList());
    }
    private Map<String, Object> makeGameViewDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("gameId", gamePlayer.getGame().getId());
        dto.put("gamePlayerId", gamePlayer.getId());
        dto.put("created", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
                .stream()
                .sorted(Comparator.comparing(GamePlayer::getId))
                .map(this::makeGamePlayerDTO)
                .collect(Collectors.toList()));

        List<Map<String, Object>> shipsDTO = gamePlayer.getShips()
                .stream()
                .map(this::makeShipsDTO)
                .collect(Collectors.toList());
        List<String> shipTypes = getShipTypes(gamePlayer);
        dto.put("ships", shipsDTO);

        return dto;
    }

}
