package com.codeoftheweb.salvo.controller;


import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GameRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gamePlayerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/games")
    public Map<String, Object> getGamesPageData(Authentication authentication) {
        Map<String, Object> mapOfGamesPage = new HashMap<>();
        Map<String, Object> mappedPlayer = new HashMap<>();
        if (authentication != null) {
            Player authenticatedPlayer = this.getAuthenticatedUser(authentication);
            mappedPlayer = this.makePlayersDTO(authenticatedPlayer);
        }
        mapOfGamesPage.put("player", mappedPlayer);
        mapOfGamesPage.put("games", this.getGames());
        return mapOfGamesPage;
    }

    @RequestMapping(path = "/api/players", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@Valid @RequestBody Player player) {
        if (player.getUsername().isEmpty() || player.getPassword().isEmpty()) {
            return new ResponseEntity<>("No name given", HttpStatus.FORBIDDEN);
        }
        if (playerRepository.findByUsername(player.getUsername()) != null) {
            return new ResponseEntity<>("Name already used", HttpStatus.CONFLICT);
        }
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);
        return new ResponseEntity<>("Name added", HttpStatus.CREATED);
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    private Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = this.gamePlayerRepository.findById(gamePlayerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no GamePlayer found with this id."));
        Game game = gamePlayer.getGame();
        Map<String, Object> mapOfGameView = this.makeGameDTO(game);
        mapOfGameView.put("gamePlayerId", gamePlayerId);
        mapOfGameView.put("ships", this.makeShipsDTO(gamePlayer.getShips()));
        mapOfGameView.put("salvoes", this.makeSalvoesDTO(gamePlayer));
        return new TreeMap<>(mapOfGameView);
    }

    private Player getAuthenticatedUser (Authentication authentication) {
        return this.playerRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this username"));
    }

    private List<Object> getGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(this::makeGameDTO)
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getCreationDate());
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
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayersDTO(gamePlayer.getPlayer()));
        dto.put("score", this.gamePlayerScore(gamePlayer));
        return dto;
    }


    private Map<String, Object> makePlayersDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("username", player.getUsername());
        return dto;
    }

    private Double gamePlayerScore(GamePlayer gamePlayer) {
        List<Score> scoresOfGame = gamePlayer.getGame().getScores();
        Long playerId = gamePlayer.getPlayer().getId();
        Optional<Score> scoreOfGamePlayer = scoresOfGame.stream()
                .filter(score -> Objects.equals(score.getPlayer().getId(), playerId))
                .findFirst();
        return scoreOfGamePlayer.map(Score::getScore).orElse(null);
    }

    private List<Object> makeShipsDTO(Set<Ship> ships) {
        return ships.stream()
                .map(ship -> {
                    Map<String, Object> shipMap = new LinkedHashMap<>();
                    shipMap.put("shipType", ship.getShipType());
                    shipMap.put("shipLocations", ship.getShipLocations()
                            .stream()
                            .map(ShipLocation::getGridCell));
                    return shipMap;
                })
                .collect(Collectors.toList());
    }

    private Map<Long, Object> makeSalvoesDTO(GamePlayer gamePlayerRequested) {
        Map<Long, Object> mapOfSalvoes = new HashMap<>();
        Set<GamePlayer> gamePlayers = gamePlayerRequested.getGame().getGamePlayers();
        gamePlayers.forEach(gamePlayer ->
                mapOfSalvoes.put(gamePlayer.getPlayer().getId(), this.makeSalvoTurnsAndLocations(gamePlayer.getSalvoes())));
        return mapOfSalvoes;
    }

    private Map<Integer, Object> makeSalvoTurnsAndLocations(Set<Salvo> salvoes) {
        Map<Integer, Object> mapOfLocations = new HashMap<>();
        salvoes.forEach(salvo ->
                mapOfLocations.put(salvo.getTurnNumber(), salvo.getSalvoLocations()
                        .stream()
                        .map(SalvoLocation::getGridCell)));
        return mapOfLocations;
    }

    private Map<String, Object> checkInfo(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
