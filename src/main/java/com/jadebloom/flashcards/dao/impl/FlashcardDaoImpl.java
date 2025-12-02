package com.jadebloom.flashcards.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.jadebloom.flashcards.dao.FlashcardDao;
import com.jadebloom.flashcards.domain.Flashcard;

@Component
public class FlashcardDaoImpl implements FlashcardDao {

    private final JdbcTemplate jdbcTemplate;

    public FlashcardDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(Flashcard category) {
        String SQL = "INSERT INTO flashcard " +
                "(flashcard_category_id, front_text, back_text) " +
                "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, category.getFlashcardCategoryId());
            ps.setString(2, category.getFrontText());
            ps.setString(3, category.getBackText());

            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    @Override
    public List<Flashcard> findAll() {
        FlashcardRowMapper flashcardRowMapper = new FlashcardRowMapper();

        String SQL = "SELECT id, flashcard_category_id, front_text, back_text FROM flashcard";

        return jdbcTemplate.query(SQL, flashcardRowMapper);
    }

    @Override
    public Optional<Flashcard> find(Long id) {
        FlashcardRowMapper flashcardRowMapper = new FlashcardRowMapper();

        String SQL = "SELECT id, flashcard_category_id, front_text, back_text FROM flashcard " +
                "WHERE id = ? LIMIT 1";

        List<Flashcard> flashcards = jdbcTemplate.query(SQL, flashcardRowMapper, id);

        return flashcards.stream().findFirst();
    }

    @Override
    public void update(Long id, Flashcard flashcardCategory) {
        String SQL = "UPDATE flashcard SET " +
                "flashcard_category_id = ?, " +
                "front_text = ?, " +
                "back_text = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(
                SQL,
                flashcardCategory.getFlashcardCategoryId(),
                flashcardCategory.getFrontText(),
                flashcardCategory.getBackText(),
                flashcardCategory.getId());
    }

    @Override
    public int deleteAll() {
        String SQL = "DELETE FROM flashcard";

        return jdbcTemplate.update(SQL);
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM flashcard WHERE id = ?";

        jdbcTemplate.update(SQL, id);
    }

    public static class FlashcardRowMapper implements RowMapper<Flashcard> {

        @Override
        public Flashcard mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flashcard flashcard = new Flashcard(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getString(4));

            return flashcard;
        }

    }

}
