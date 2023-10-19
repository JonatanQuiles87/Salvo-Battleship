package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<Map<String, Object>> getGames() {
        List<Game> games = gameRepository.findAll();
        List<Map<String, Object>> gamesData = new ArrayList<>();

        for (Game game : games) {
            Map<String, Object> gameData = new HashMap<>();
            gameData.put("id", game.getId());
            gameData.put("created", game.getCreationDate());

            // Create a list of game player IDs
            List<Long> gamePlayerIds = new ArrayList<>();
            for (GamePlayer gamePlayer : game.getGamePlayers()) {
                gamePlayerIds.add(gamePlayer.getId());
            }

            gameData.put("gamePlayerIds", gamePlayerIds);

            // Add player information here
            List<Map<String, Object>> gamePlayerData = new ArrayList<>();
            for (GamePlayer gamePlayer : game.getGamePlayers()) {
                Map<String, Object> playerData = new HashMap<>();
                playerData.put("gamePlayerId", gamePlayer.getId());

                // Add player information here
                Player player = gamePlayer.getPlayer();
                Map<String, Object> playerInfo = new HashMap<>();
                playerInfo.put("playerId", player.getId());
                playerInfo.put("playerEmail", player.getEmail()); // Assuming you have a method to get the player's email

                playerData.put("player", playerInfo);
                gamePlayerData.add(playerData);
            }

            gameData.put("gamePlayers", gamePlayerData);
            gamesData.add(gameData);
        }

        return gamesData;
    }

}
