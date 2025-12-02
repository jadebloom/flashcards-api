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
import com.jadebloom.flashcards.domain.FlashcardCategory;

@SpringBootTest
@ExtendWith({ SpringExtension.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlashcardCategoryDaoImplIntegrationTests {

    private final FlashcardCategoryDaoImpl underTest;

    @Autowired
    public FlashcardCategoryDaoImplIntegrationTests(FlashcardCategoryDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatMultipleFlashcardCategoriesCanBeCreatedCorrectly() {
        FlashcardCategory fc1 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc2 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc3 = TestDaoUtils.createTestFlashcardCategory();

        fc1.setId(underTest.create(fc1));
        fc2.setId(underTest.create(fc2));
        fc3.setId(underTest.create(fc3));

        List<FlashcardCategory> flashcardCategories = underTest.findAll();

        assertAll("Assert that multiple created flashcard categories can be created correctly",
                () -> assertEquals(3, flashcardCategories.size()),
                () -> assertTrue(flashcardCategories.contains(fc1)),
                () -> assertTrue(flashcardCategories.contains(fc2)),
                () -> assertTrue(flashcardCategories.contains(fc3)));
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertAll("Assert that a flashcard category can be created correctly",
                () -> assertTrue(foundFlashcardCategory.isPresent()),
                () -> assertEquals(flashcardCategory, foundFlashcardCategory.get()));
    }

    @Test
    public void testThatFlashcardCategoryCanBeUpdatedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);
        flashcardCategory.setName("English Phrases");

        underTest.update(id, flashcardCategory);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertAll("Assert that a created flashcard category can be updated correctly",
                () -> assertTrue(foundFlashcardCategory.isPresent()),
                () -> assertEquals(flashcardCategory, foundFlashcardCategory.get()));
    }

    @Test
    public void testThatAllFlashcardCategoriesCanBeDeletedCorrectly() {
        FlashcardCategory fc1 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc2 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc3 = TestDaoUtils.createTestFlashcardCategory();

        underTest.create(fc1);
        underTest.create(fc2);
        underTest.create(fc3);

        int deletionCount = underTest.deleteAll();

        List<FlashcardCategory> flashcardCategories = underTest.findAll();

        assertAll("Assert that all flashcard categories can be deleted correctly",
                () -> assertEquals(deletionCount, 3),
                () -> assertTrue(flashcardCategories.isEmpty()));
    }

    @Test
    public void testThatFlashcardCategoryCanBeDeletedCorrectly() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);

        underTest.delete(id);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertTrue(foundFlashcardCategory.isEmpty());
    }

}
