package com.jadebloom.flashcards.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlashcardCategoryRepositoryIntegrationTests {

    private final FlashcardCategoryRepository underTest;

    @Autowired
    public FlashcardCategoryRepositoryIntegrationTests(FlashcardCategoryRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatMultipleFlashcardCategoriesCanBeCreatedAndFound() {
        FlashcardCategoryEntity entity1 = new FlashcardCategoryEntity("Kanji");
        FlashcardCategoryEntity savedEntity1 = underTest.save(entity1);

        FlashcardCategoryEntity entity2 = new FlashcardCategoryEntity("French");
        FlashcardCategoryEntity savedEntity2 = underTest.save(entity2);

        FlashcardCategoryEntity entity3 = new FlashcardCategoryEntity("English");
        FlashcardCategoryEntity savedEntity3 = underTest.save(entity3);

        Page<FlashcardCategoryEntity> page = underTest.findAll(PageRequest.of(0, 3));

        List<FlashcardCategoryEntity> foundFlashcardCategoryEntities = page.getContent();

        assertAll("Assert that multiple flashcard categories can be created and found",
                () -> assertEquals(3, foundFlashcardCategoryEntities.size()),
                () -> assertTrue(foundFlashcardCategoryEntities.contains(savedEntity1)),
                () -> assertTrue(foundFlashcardCategoryEntities.contains(savedEntity2)),
                () -> assertTrue(foundFlashcardCategoryEntities.contains(savedEntity3)));
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedAndFound() {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");
        FlashcardCategoryEntity savedFlashcardCategoryEntity = underTest.save(flashcardCategoryEntity);

        Long id = savedFlashcardCategoryEntity.getId();

        Optional<FlashcardCategoryEntity> foundFlashcardCategoryEntity = underTest.findById(id);

        assertAll("Assert that a flashcard category can be created and found",
                () -> assertTrue(foundFlashcardCategoryEntity.isPresent()),
                () -> assertEquals(flashcardCategoryEntity, foundFlashcardCategoryEntity.get()));
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedAndDeletedByItsId() {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        FlashcardCategoryEntity savedFlashcardCategoryEntity = underTest.save(flashcardCategoryEntity);

        Long id = savedFlashcardCategoryEntity.getId();

        underTest.deleteById(id);

        Optional<FlashcardCategoryEntity> foundFlashcardCategoryEntity = underTest.findById(id);

        assertTrue(foundFlashcardCategoryEntity.isEmpty());
    }

}
