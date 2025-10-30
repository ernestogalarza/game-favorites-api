package com.comeon.ernesto.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comeon.ernesto.game.model.entity.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> { }
