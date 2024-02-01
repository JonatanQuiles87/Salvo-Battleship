package com.codeoftheweb.salvo.repositories;

import java.util.List;

import com.codeoftheweb.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByEmail (String email);
}
