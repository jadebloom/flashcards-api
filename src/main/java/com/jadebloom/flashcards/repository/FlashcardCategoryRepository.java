package com.jadebloom.flashcards.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;

@Repository
public interface FlashcardCategoryRepository extends CrudRepository<FlashcardCategoryEntity, Long>,
        PagingAndSortingRepository<FlashcardCategoryEntity, Long> {
}
