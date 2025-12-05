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
import com.jadebloom.flashcards.domain.entity.FlashcardEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlashcardRepositoryIntegrationTests {

    private final FlashcardCategoryRepository flashcardCategoryRepository;

    private final FlashcardRepository underTest;

    @Autowired
    public FlashcardRepositoryIntegrationTests(
            FlashcardCategoryRepository flashcardCategoryRepository,
            FlashcardRepository underTest) {
        this.flashcardCategoryRepository = flashcardCategoryRepository;

        this.underTest = underTest;
    }

    @Test
    public void testThatMultipleFlashcardsWithValidFlashcardCategoryCanBeCreatedAndFound() {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        FlashcardCategoryEntity savedFlashcardCategoryEntity = flashcardCategoryRepository
                .save(flashcardCategoryEntity);

        FlashcardEntity e1 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "通勤",
                "Commute");

        FlashcardEntity e2 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "歴史",
                "History");

        FlashcardEntity e3 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "夢",
                "Dream");

        FlashcardEntity se1 = underTest.save(e1);
        FlashcardEntity se2 = underTest.save(e2);
        FlashcardEntity se3 = underTest.save(e3);

        Page<FlashcardEntity> page = underTest.findAll(PageRequest.of(0, 3));

        List<FlashcardEntity> foundFlashcardEntities = page.getContent();

        assertAll(
                "Assert that multiple flashcard with a valid flashcard category can be created and found",
                () -> assertEquals(3, foundFlashcardEntities.size()),
                () -> assertTrue(foundFlashcardEntities.contains(se1)),
                () -> assertTrue(foundFlashcardEntities.contains(se2)),
                () -> assertTrue(foundFlashcardEntities.contains(se3)));
    }

    @Test
    public void testThatFlashcardsWithTheSameFlashcardCategoryCanBeCreatedAndFoundByIt() {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        FlashcardCategoryEntity savedFlashcardCategoryEntity = flashcardCategoryRepository
                .save(flashcardCategoryEntity);

        FlashcardEntity e1 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "通勤",
                "Commute");

        FlashcardEntity e2 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "歴史",
                "History");

        FlashcardEntity e3 = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "夢",
                "Dream");

        FlashcardEntity se1 = underTest.save(e1);
        FlashcardEntity se2 = underTest.save(e2);
        FlashcardEntity se3 = underTest.save(e3);

        Page<FlashcardEntity> page = underTest.findByFlashcardCategoryId(
                savedFlashcardCategoryEntity.getId(),
                PageRequest.of(0, 3));

        List<FlashcardEntity> foundFlashcardEntities = page.getContent();

        assertAll(
                "Assert that multiple flashcards with the same flashcard category can be created and found by it",
                () -> assertEquals(3, foundFlashcardEntities.size()),
                () -> assertTrue(foundFlashcardEntities.contains(se1)),
                () -> assertTrue(foundFlashcardEntities.contains(se2)),
                () -> assertTrue(foundFlashcardEntities.contains(se3)));
    }

    @Test
    public void testThatFlashcardWithValidFlashcardCategoryCanBeCreatedAndFound() {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        FlashcardCategoryEntity savedFlashcardCategoryEntity = flashcardCategoryRepository
                .save(flashcardCategoryEntity);

        FlashcardEntity flashcardEntity = new FlashcardEntity(
                savedFlashcardCategoryEntity,
                "歴史",
                "History");

        FlashcardEntity savedFlashcardEntity = underTest.save(flashcardEntity);

        Optional<FlashcardEntity> foundFlashcardEntity = underTest.findById(savedFlashcardEntity.getId());

        assertAll(
                "Assert that a flashcard with a valid flashcard category can be created and found",
                () -> assertTrue(foundFlashcardEntity.isPresent()),
                () -> assertEquals(savedFlashcardEntity, foundFlashcardEntity.get()));
    }

}
