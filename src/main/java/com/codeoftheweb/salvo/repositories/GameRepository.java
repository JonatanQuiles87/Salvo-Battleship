    package com.codeoftheweb.salvo.repositories;

    import com.codeoftheweb.salvo.model.Game;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.rest.core.annotation.RepositoryRestResource;

    import java.util.Optional;


    @RepositoryRestResource
    public interface GameRepository extends JpaRepository<Game, Long> {
        Optional<Game> findById(Long gameId);
    }
