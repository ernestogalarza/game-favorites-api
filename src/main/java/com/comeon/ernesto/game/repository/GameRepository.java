package com.comeon.ernesto.game.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.comeon.ernesto.game.model.entity.GameEntity;


public interface GameRepository  extends JpaRepository<GameEntity, Long> {

    Page<GameEntity> findAll(Pageable pageable);

}
