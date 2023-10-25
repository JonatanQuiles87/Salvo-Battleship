package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public List<Map<String, Object>> getGamesDTO() {
        List<Game> games = gameRepository.findAll();

        return games.stream()
                .map(this::makeGameDTO)
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> gameData = new HashMap<>();
        gameData.put("id", game.getId());
        gameData.put("created", game.getCreationDate());
        gameData.put("gamePlayerIds", game.getGamePlayers().stream()
                .map(GamePlayer::getId)
                .collect(Collectors.toList()));
        gameData.put("gamePlayers", game.getGamePlayers().stream()
                .map(this::makeGamePlayerDTO)
                .collect(Collectors.toList()));

        return gameData;
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> playerData = new HashMap<>();
        playerData.put("gamePlayerId", gamePlayer.getId());

        Player player = gamePlayer.getPlayer();
        Map<String, Object> playerInfo = new HashMap<>();
        playerInfo.put("playerId", player.getId());
        playerInfo.put("playerEmail", player.getEmail()); // Assuming you have a method to get the player's email

        playerData.put("player", playerInfo);
        return playerData;
    }

}
