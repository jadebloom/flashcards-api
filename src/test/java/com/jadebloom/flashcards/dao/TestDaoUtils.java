package com.jadebloom.flashcards.dao;

import com.jadebloom.flashcards.domain.Flashcard;
import com.jadebloom.flashcards.domain.FlashcardCategory;

public class TestDaoUtils {

    private TestDaoUtils() {
    }

    public static FlashcardCategory createTestFlashcardCategory() {
        FlashcardCategory flashcardCategory = new FlashcardCategory("Japanese Kanji");

        return flashcardCategory;
    }

    public static Flashcard createTestFlashcard(Long flashcardCategoryId) {
        Flashcard flashcard = new Flashcard(
                flashcardCategoryId,
                "神社",
                "Sento shrine");

        return flashcard;
    }

}
