package com.jadebloom.flashcards.controller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jadebloom.flashcards.domain.dto.FlashcardCategoryDto;
import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;
import com.jadebloom.flashcards.service.FlashcardCategoryService;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class FlashcardCategoryControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final FlashcardCategoryService flashcardCategoryService;

    @Autowired
    public FlashcardCategoryControllerIntegrationTests(
            MockMvc mockMvc,
            @Qualifier("flashcardCategoryServiceImpl") FlashcardCategoryService flashcardCategoryService) {
        this.mockMvc = mockMvc;

        this.objectMapper = new ObjectMapper();

        this.flashcardCategoryService = flashcardCategoryService;
    }

    @Test
    public void testThatFlashcardCategoryCanBeCreatedAndReturned() throws Exception {
        FlashcardCategoryDto flashcardCategoryDto = new FlashcardCategoryDto("Kanji");

        String json = objectMapper.writeValueAsString(flashcardCategoryDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/flashcards/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name")
                                .value(flashcardCategoryDto.getName()));
    }

    @Test
    public void testThatHttp200OkCanBeReturnedWhenEmptyList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/flashcards/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatExistingFlashcardCategoryCanBeReturned() throws Exception {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        FlashcardCategoryEntity savedFlashcardCategoryEntity = flashcardCategoryService
                .save(flashcardCategoryEntity);

        Long flashcardCategoryId = savedFlashcardCategoryEntity.getId();

        Optional<FlashcardCategoryEntity> foundFlashcardCategoryEntity = flashcardCategoryService
                .findById(flashcardCategoryId);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/flashcards/categories/" + flashcardCategoryId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(flashcardCategoryId))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name")
                                .value(flashcardCategoryEntity.getName()))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name")
                                .value(savedFlashcardCategoryEntity.getName()))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name")
                                .value(foundFlashcardCategoryEntity.get().getName()));
    }

    @Test
    public void testThatExistingFlashcardCategoryCanBeDeleted() throws Exception {
        FlashcardCategoryEntity flashcardCategoryEntity = new FlashcardCategoryEntity("Kanji");

        Long id = flashcardCategoryService.save(flashcardCategoryEntity).getId();

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/flashcards/categories/" + id)).andExpect(
                        MockMvcResultMatchers.status().isNoContent());
    }

}
