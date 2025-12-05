package com.jadebloom.flashcards.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jadebloom.flashcards.domain.entity.FlashcardEntity;
import com.jadebloom.flashcards.repository.FlashcardRepository;
import com.jadebloom.flashcards.service.FlashcardService;

@Service
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository flashcardRepository;

    public FlashcardServiceImpl(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    public FlashcardEntity save(FlashcardEntity flashcardEntity) {
        return flashcardRepository.save(flashcardEntity);
    }

    @Override
    public Page<FlashcardEntity> findAll(Pageable pageable) {
        return flashcardRepository.findAll(pageable);
    }

    @Override
    public Page<FlashcardEntity> findAllByFlashcardCategoryId(Long flashcardCategoryId, Pageable pageable) {
        return flashcardRepository.findByFlashcardCategoryId(flashcardCategoryId, pageable);
    }

    @Override
    public Optional<FlashcardEntity> findById(Long id) {
        return flashcardRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        flashcardRepository.deleteById(id);
    }

}
