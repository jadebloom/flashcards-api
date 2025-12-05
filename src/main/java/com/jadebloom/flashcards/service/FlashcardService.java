package com.jadebloom.flashcards.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jadebloom.flashcards.domain.entity.FlashcardEntity;

public interface FlashcardService {

    FlashcardEntity save(FlashcardEntity FlashcardEntity);

    Page<FlashcardEntity> findAll(Pageable pageable);

    Page<FlashcardEntity> findAllByFlashcardCategoryId(Long flashcardCategoryId, Pageable pageable);

    Optional<FlashcardEntity> findById(Long id);

    void deleteById(Long id);

}
