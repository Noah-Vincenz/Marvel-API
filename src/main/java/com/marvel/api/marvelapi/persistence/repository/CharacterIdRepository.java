package com.marvel.api.marvelapi.persistence.repository;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterIdRepository extends JpaRepository<CharacterIdEntity, Integer> {}
