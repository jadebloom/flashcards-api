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

import com.jadebloom.flashcards.dao.FlashcardCategoryDao;
import com.jadebloom.flashcards.domain.FlashcardCategory;

@Component
public class FlashcardCategoryDaoImpl implements FlashcardCategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public FlashcardCategoryDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(FlashcardCategory category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String SQL = "INSERT INTO flashcard_category (name) VALUES (?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, category.getName());

            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    @Override
    public List<FlashcardCategory> findAll() {
        FlashcardCategoryRowMapper rowMapper = new FlashcardCategoryRowMapper();

        String SQL = "SELECT id, name FROM flashcard_category";

        return jdbcTemplate.query(SQL, rowMapper);
    }

    @Override
    public Optional<FlashcardCategory> findOne(Long id) {
        FlashcardCategoryRowMapper rowMapper = new FlashcardCategoryRowMapper();

        String SQL = "SELECT id, name FROM flashcard_category WHERE id = ? LIMIT 1";

        List<FlashcardCategory> categories = jdbcTemplate.query(SQL, rowMapper, new Object[] { id });

        return categories.stream().findFirst();
    }

    @Override
    public void update(Long id, FlashcardCategory flashcardCategory) {
        String SQL = "UPDATE flashcard_category SET name = ? WHERE id = ?";

        jdbcTemplate.update(SQL, flashcardCategory.getName(), id);
    }

    @Override
    public int deleteAll() {
        String SQL = "DELETE FROM flashcard_category";

        return jdbcTemplate.update(SQL);
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM flashcard_category WHERE id = ?";

        jdbcTemplate.update(SQL, id);
    }

    public static class FlashcardCategoryRowMapper implements RowMapper<FlashcardCategory> {
        @Override
        public FlashcardCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            FlashcardCategory category = new FlashcardCategory(rs.getString(2));

            return category;
        }
    }

}
