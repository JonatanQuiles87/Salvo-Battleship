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
    public List<Map<String, Object>> getGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream()
                .map(this::gameToDataObject) // Use the private method to convert Game to Map
                .collect(Collectors.toList());
    }

    // Private method to convert a Game object to a Map
    private Map<String, Object> gameToDataObject(Game game) {
        Map<String, Object> dataObject = new HashMap<>();
        dataObject.put("id", game.getId());
        dataObject.put("created", game.getCreationDate()); // Assuming you have a method to get the creation date

        return dataObject;
    }
}
