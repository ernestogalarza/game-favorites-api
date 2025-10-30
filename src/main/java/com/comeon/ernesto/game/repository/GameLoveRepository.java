package com.comeon.ernesto.game.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comeon.ernesto.game.model.dto.GameDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteDto;
import com.comeon.ernesto.game.model.entity.GameLoveEntity;

public interface GameLoveRepository extends JpaRepository<GameLoveEntity, Long> {

    @Query("""
            SELECT new com.comeon.ernesto.game.model.dto.GameDto(
                       g.id,
                       g.name
                   )
           FROM GameLoveEntity gl
           JOIN gl.game g
            WHERE gl.player.id = :playerId""")
    List<GameDto> findGamesLovedByPlayer(@Param("playerId") Long playerId);

    @Query("""
    SELECT new com.comeon.ernesto.game.model.dto.GameFavoriteDto(
        gl.game.id,
        gl.game.name,
        COUNT(gl)
    )
    FROM GameLoveEntity gl
    GROUP BY gl.game.id, gl.game.name
    ORDER BY COUNT(gl) DESC
        """)
    List<GameFavoriteDto> findTopLovedGames(Pageable pageable);
    Optional<GameLoveEntity> findByPlayerIdAndGameId(Long playerId, Long gameId);

}
