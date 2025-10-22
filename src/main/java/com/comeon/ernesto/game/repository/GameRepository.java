package com.comeon.ernesto.game.repository;

import com.comeon.ernesto.game.model.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository  extends JpaRepository<GameEntity, Long> {

    Page<GameEntity> findAll(Pageable pageable);

}
