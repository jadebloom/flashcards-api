package com.jadebloom.flashcards.dao;

import java.util.List;
import java.util.Optional;

import com.jadebloom.flashcards.domain.Flashcard;

public interface FlashcardDao {
    public Long create(Flashcard category);

    public List<Flashcard> findAll();

    public Optional<Flashcard> find(Long id);

    public void update(Long id, Flashcard flashcardCategory);

    public int deleteAll();

    public void delete(Long id);

}
