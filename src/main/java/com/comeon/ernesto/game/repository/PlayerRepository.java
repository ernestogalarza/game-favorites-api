package com.comeon.ernesto.game.repository;

import com.comeon.ernesto.game.model.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {}