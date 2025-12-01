package com.jadebloom.flashcards.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;

import com.jadebloom.flashcards.dao.TestDaoUtils;
import com.jadebloom.flashcards.domain.FlashcardCategory;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FlashcardCategoryDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private FlashcardCategoryDaoImpl underTest;

    @Test
    public void testThatCreateGeneratesTheCorrectSql() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        underTest.create(flashcardCategory);

        verify(jdbcTemplate).update(
                ArgumentMatchers.<PreparedStatementCreator>any(),
                ArgumentMatchers.<KeyHolder>any());
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql() {
        underTest.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT id, name FROM flashcard_category"),
                ArgumentMatchers.<FlashcardCategoryDaoImpl.FlashcardCategoryRowMapper>any());
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name FROM flashcard_category WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<FlashcardCategoryDaoImpl.FlashcardCategoryRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql() {
        FlashcardCategory flashcardCategory = TestDaoUtils.createTestFlashcardCategory();

        underTest.update(flashcardCategory.getId(), flashcardCategory);

        verify(jdbcTemplate).update(
                eq("UPDATE flashcard_category SET name = ? WHERE id = ?"),
                eq(flashcardCategory.getName()),
                eq(flashcardCategory.getId()));
    }

    @Test
    public void testThatDeleteOneGeneratesTheCorrectSql() {
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM flashcard_category WHERE id = ?"),
                eq(1L));
    }

    @Test
    public void testThatDeleteManyGeneratesTheCorrectSql() {
        underTest.deleteAll();

        verify(jdbcTemplate).update(
                eq("DELETE FROM flashcard_category"));
    }

}
