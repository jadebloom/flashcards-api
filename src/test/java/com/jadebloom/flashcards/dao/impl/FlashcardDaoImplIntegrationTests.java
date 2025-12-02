package com.jadebloom.flashcards.dao.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jadebloom.flashcards.dao.TestDaoUtils;
import com.jadebloom.flashcards.domain.Flashcard;
import com.jadebloom.flashcards.domain.FlashcardCategory;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlashcardDaoImplIntegrationTests {

    private final FlashcardCategoryDaoImpl helper;

    private final FlashcardDaoImpl underTest;

    @Autowired
    public FlashcardDaoImplIntegrationTests(
            FlashcardCategoryDaoImpl helper,
            FlashcardDaoImpl underTest) {
        this.helper = helper;

        this.underTest = underTest;
    }

    @Test
    public void testThatMultipleFlashcardsCanBeCreatedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long flashcardCategoryId = helper.create(flashcardCategory);

        Flashcard f1 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);
        Flashcard f2 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);
        Flashcard f3 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);

        f1.setId(underTest.create(f1));
        f2.setId(underTest.create(f2));
        f3.setId(underTest.create(f3));

        List<Flashcard> flashcards = underTest.findAll();

        assertAll(
                "Assert that multiple flashcards can be created correctly",
                () -> assertEquals(3, flashcards.size()),
                () -> assertTrue(flashcards.contains(f1)),
                () -> assertTrue(flashcards.contains(f2)),
                () -> assertTrue(flashcards.contains(f3)));
    }

    @Test
    public void testThatFlashcardCanBeCreatedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long flashcardCategoryId = helper.create(flashcardCategory);

        Flashcard flashcard = TestDaoUtils.createTestFlashcard(flashcardCategoryId);

        Long flashcardId = underTest.create(flashcard);

        flashcard.setId(flashcardId);

        Optional<Flashcard> foundFlashcard = underTest.find(flashcardId);

        System.out.println(flashcard);
        System.out.println(foundFlashcard.get());

        assertAll(
                "Assert that a flashcard can be created correctly",
                () -> assertTrue(foundFlashcard.isPresent()),
                () -> assertEquals(flashcard, foundFlashcard.get()));
    }

    @Test
    public void testThatFlashcardCanBeUpdatedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long flashcardCategoryId = helper.create(flashcardCategory);

        Flashcard flashcard = TestDaoUtils.createTestFlashcard(flashcardCategoryId);

        Long flashcardId = underTest.create(flashcard);

        flashcard.setId(flashcardId);
        flashcard.setBackText("Divine place");

        underTest.update(flashcardId, flashcard);

        Optional<Flashcard> foundFlashcard = underTest.find(flashcardId);

        assertAll(
                "Assert that a flashcard can be updated correctly",
                () -> assertTrue(foundFlashcard.isPresent()),
                () -> assertEquals(flashcard, foundFlashcard.get()));
    }

    @Test
    public void testThatAllFlashcardsCanBeDeletedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long flashcardCategoryId = helper.create(flashcardCategory);

        Flashcard f1 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);
        Flashcard f2 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);
        Flashcard f3 = TestDaoUtils.createTestFlashcard(flashcardCategoryId);

        underTest.create(f1);
        underTest.create(f2);
        underTest.create(f3);

        int deletionCount = underTest.deleteAll();

        List<Flashcard> flashcards = underTest.findAll();

        assertAll(
                "Assert that all flashcards can be deleted correctly",
                () -> assertEquals(3, deletionCount),
                () -> assertTrue(flashcards.isEmpty()));
    }

    @Test
    public void testThatFlashcardCanBeDeletedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long flashcardCategoryId = helper.create(flashcardCategory);

        Flashcard flashcard = TestDaoUtils.createTestFlashcard(flashcardCategoryId);

        Long flashcardId = underTest.create(flashcard);

        underTest.delete(flashcardId);

        Optional<Flashcard> foundFlashcard = underTest.find(flashcardId);

        assertTrue(foundFlashcard.isEmpty());
    }

}
