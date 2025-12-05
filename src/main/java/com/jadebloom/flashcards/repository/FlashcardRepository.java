package com.jadebloom.flashcards.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jadebloom.flashcards.domain.entity.FlashcardEntity;

@Repository
public interface FlashcardRepository
                extends CrudRepository<FlashcardEntity, Long>, PagingAndSortingRepository<FlashcardEntity, Long> {

        public Page<FlashcardEntity> findByFlashcardCategoryId(
                        Long flashcardCategoryId, Pageable pageable);

}
