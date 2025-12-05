package com.jadebloom.flashcards.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;

public interface FlashcardCategoryService {

    FlashcardCategoryEntity save(FlashcardCategoryEntity flashcardCategoryEntity);

    Page<FlashcardCategoryEntity> findAll(Pageable pageable);

    Optional<FlashcardCategoryEntity> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

}
