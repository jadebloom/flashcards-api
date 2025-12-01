package com.jadebloom.flashcards.dao;

import com.jadebloom.flashcards.domain.FlashcardCategory;

public class TestDaoUtils {

    public static FlashcardCategory createTestFlashcardCategory() {
        FlashcardCategory flashcardCategory = new FlashcardCategory("Japanese Kanji");

        return flashcardCategory;
    }

}
