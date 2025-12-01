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
    public void testThatMultipleFlashcardCategoriesCanBeCreatedAndFound() {
        FlashcardCategory fc1 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc2 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc3 = TestDaoUtils.createTestFlashcardCategory();

        Long id1 = underTest.create(fc1);
        Long id2 = underTest.create(fc2);
        Long id3 = underTest.create(fc3);

        fc1.setId(id1);
        fc2.setId(id2);
        fc3.setId(id3);

        List<FlashcardCategory> flashcardCategories = underTest.findAll();

        assertAll("Assert that three created flashcard categories are found and matched",
                () -> assertEquals(3, flashcardCategories.size()),
                () -> assertTrue(flashcardCategories.contains(fc1)),
                () -> assertTrue(flashcardCategories.contains(fc2)),
                () -> assertTrue(flashcardCategories.contains(fc3)));
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedAndFound() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertAll("Assert that created flashcard category is found and matched",
                () -> assertTrue(foundFlashcardCategory.isPresent()),
                () -> assertEquals(flashcardCategory, foundFlashcardCategory.get()));
    }

    @Test
    public void testThatFlashcardCategoryCanBeUpdated() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);
        flashcardCategory.setName("English Phrases");

        underTest.update(id, flashcardCategory);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertAll("Assert that updated flashcard category is found and matched",
                () -> assertTrue(foundFlashcardCategory.isPresent()),
                () -> assertEquals(flashcardCategory, foundFlashcardCategory.get()));
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedAndDeleted() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);

        underTest.delete(id);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertTrue(foundFlashcardCategory.isEmpty());
    }

    @Test
    public void testThatFlashcardCanBeCreatedAndDeleted() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        Long id = underTest.create(flashcardCategory);

        flashcardCategory.setId(id);

        underTest.delete(id);

        Optional<FlashcardCategory> foundFlashcardCategory = underTest.findOne(id);

        assertTrue(foundFlashcardCategory.isEmpty());
    }

    @Test
    public void testThatAllFlashcardCategoriesCanBeCreatedAndDeleted() {
        FlashcardCategory fc1 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc2 = TestDaoUtils.createTestFlashcardCategory();
        FlashcardCategory fc3 = TestDaoUtils.createTestFlashcardCategory();

        underTest.create(fc1);
        underTest.create(fc2);
        underTest.create(fc3);

        int deletionCount = underTest.deleteAll();

        List<FlashcardCategory> flashcardCategories = underTest.findAll();

        assertAll("Assert that all created flashcard categories are deleted",
                () -> assertEquals(deletionCount, 3),
                () -> assertTrue(flashcardCategories.isEmpty()));
    }

}
