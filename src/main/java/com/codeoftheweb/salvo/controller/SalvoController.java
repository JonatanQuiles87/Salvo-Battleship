package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.dto.PlayerRequest;
import com.codeoftheweb.salvo.model.dto.PlayerResponse;
import com.codeoftheweb.salvo.model.dto.ShipDtoListWrapper;
import com.codeoftheweb.salvo.service.SalvoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SalvoController {

    private final SalvoService salvoService;

    public SalvoController(SalvoService salvoService) {
        this.salvoService = salvoService;
    }

    @GetMapping("/games")
    public Map<String, Object> getGames(Authentication authentication) {
        return this.salvoService.getGamesPageData(authentication);
    }

    @GetMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId, Authentication authentication) {
        return this.salvoService.getGameView(gamePlayerId, authentication);
    }

    @PostMapping("/players")
    public PlayerResponse signUp(@Valid @RequestBody PlayerRequest playerRequest) {
        return this.salvoService.signUp(playerRequest);
    }

    @PostMapping("/games")
    public ResponseEntity<Map<String, Long>> createGame(Authentication authentication) {
        return ResponseEntity.created(URI.create("/game_view/id")).body(this.salvoService.createGame(authentication));
    }

    @PostMapping("/game/{gameId}/players")
    public ResponseEntity<Map<String, Long>> joinGame(@PathVariable Long gameId, Authentication authentication) {
        return ResponseEntity.created(URI.create("game_view/gamePlayerId")).body(this.salvoService.joinGame(gameId, authentication));
    }

    @PostMapping("/games/players/{gamePlayerId}/ships")
    public ResponseEntity<Void> placeShips(@PathVariable Long gamePlayerId, @Valid @RequestBody ShipDtoListWrapper shipDtoListWrapper, Authentication authentication) {
        this.salvoService.placeShips(gamePlayerId, shipDtoListWrapper, authentication);
        return ResponseEntity.created(URI.create("game_view/gamePlayerId")).build();
    }
}
