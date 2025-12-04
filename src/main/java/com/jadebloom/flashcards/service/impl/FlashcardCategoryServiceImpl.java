package com.jadebloom.flashcards.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;
import com.jadebloom.flashcards.repository.FlashcardCategoryRepository;
import com.jadebloom.flashcards.service.FlashcardCategoryService;

@Service
public class FlashcardCategoryServiceImpl implements FlashcardCategoryService {

    private final FlashcardCategoryRepository flashcardCategoryRepository;

    public FlashcardCategoryServiceImpl(FlashcardCategoryRepository flashcardCategoryRepository) {
        this.flashcardCategoryRepository = flashcardCategoryRepository;
    }

    @Override
    public FlashcardCategoryEntity save(FlashcardCategoryEntity flashcardCategoryEntity) {
        flashcardCategoryRepository.save(flashcardCategoryEntity);

        return flashcardCategoryEntity;
    }

    @Override
    public Page<FlashcardCategoryEntity> findAll(Pageable pageable) {
        return flashcardCategoryRepository.findAll(pageable);
    }

    @Override
    public Optional<FlashcardCategoryEntity> findById(Long id) {
        return flashcardCategoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        flashcardCategoryRepository.deleteById(id);
    }

}
