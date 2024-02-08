package com.codeoftheweb.salvo.repositories;

import java.util.List;
import java.util.Optional;

import com.codeoftheweb.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername (String username);
}
