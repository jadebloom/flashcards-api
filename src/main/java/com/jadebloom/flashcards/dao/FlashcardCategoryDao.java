package com.jadebloom.flashcards.dao;

import java.util.List;
import java.util.Optional;

import com.jadebloom.flashcards.domain.FlashcardCategory;

public interface FlashcardCategoryDao {

    public Long create(FlashcardCategory category);

    public List<FlashcardCategory> findAll();

    public Optional<FlashcardCategory> findOne(Long id);

    public void update(Long id, FlashcardCategory flashcardCategory);

    public int deleteAll();

    public void delete(Long id);

}
