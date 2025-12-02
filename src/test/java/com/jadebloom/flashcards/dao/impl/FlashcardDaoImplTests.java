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
import com.jadebloom.flashcards.dao.impl.FlashcardDaoImpl.FlashcardRowMapper;
import com.jadebloom.flashcards.domain.Flashcard;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FlashcardDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private FlashcardDaoImpl underTest;

    @Test
    public void testThatCreateGeneratesTheCorrectSql() {
        Flashcard flashcard = TestDaoUtils.createTestFlashcard(1L);

        underTest.create(flashcard);

        verify(jdbcTemplate).update(
                ArgumentMatchers.<PreparedStatementCreator>any(),
                ArgumentMatchers.<KeyHolder>any());
    }

    @Test
    public void testThatFindAllGeneratesTheCorrectSql() {
        underTest.findAll();

        String SQL = "SELECT id, flashcard_category_id, front_text, back_text FROM flashcard";

        verify(jdbcTemplate).query(eq(SQL), ArgumentMatchers.<FlashcardRowMapper>any());
    }

    public void testThatFindGeneratesTheCorrectSql() {
        underTest.find(1L);

        String SQL = "SELECT id, flashcard_category_id, front_text, back_text " +
                "FROM flashcard WHERE id = ? LIMIT 1";

        verify(jdbcTemplate).query(eq(SQL), ArgumentMatchers.<FlashcardRowMapper>any(), eq(1L));
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql() {
        Flashcard flashcard = TestDaoUtils.createTestFlashcard(1L);

        underTest.update(flashcard.getId(), flashcard);

        String SQL = "UPDATE flashcard SET " +
                "flashcard_category_id = ?, " +
                "front_text = ?, " +
                "back_text = ? " +
                "WHERE id = ?";

        verify(jdbcTemplate).update(
                eq(SQL),
                eq(flashcard.getFlashcardCategoryId()),
                eq(flashcard.getFrontText()),
                eq(flashcard.getBackText()),
                eq(flashcard.getId()));
    }

    @Test
    public void testThatDeleteAllGeneratedTheCorrectSql() {
        underTest.deleteAll();

        String SQL = "DELETE FROM flashcard";

        verify(jdbcTemplate).update(eq(SQL));
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSql() {
        underTest.delete(1L);

        String SQL = "DELETE FROM flashcard WHERE id = ?";

        verify(jdbcTemplate).update(eq(SQL), eq(1L));
    }

}
