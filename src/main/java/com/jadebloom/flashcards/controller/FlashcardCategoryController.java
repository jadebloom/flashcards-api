package com.jadebloom.flashcards.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jadebloom.flashcards.domain.dto.FlashcardCategoryDto;
import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;
import com.jadebloom.flashcards.mapper.Mapper;
import com.jadebloom.flashcards.service.FlashcardCategoryService;

@RestController
@RequestMapping("/api/v1/flashcards")
public class FlashcardCategoryController {

    private final FlashcardCategoryService flashcardCategoryService;

    private final Mapper<FlashcardCategoryEntity, FlashcardCategoryDto> mapper;

    public FlashcardCategoryController(
            @Qualifier("flashcardCategoryServiceImpl") FlashcardCategoryService flashcardCategoryService,
            Mapper<FlashcardCategoryEntity, FlashcardCategoryDto> mapper) {
        this.flashcardCategoryService = flashcardCategoryService;

        this.mapper = mapper;
    }

    @PostMapping(path = "/categories")
    public ResponseEntity<FlashcardCategoryDto> createFlashcardCategory(
            @RequestBody FlashcardCategoryDto flashcardCategoryDto) {
        FlashcardCategoryEntity flashcardCategoryEntity = mapper.mapFrom(flashcardCategoryDto);

        FlashcardCategoryEntity savedFlashcardCategoryEntity = flashcardCategoryService.save(flashcardCategoryEntity);

        return new ResponseEntity<>(mapper.mapTo(savedFlashcardCategoryEntity), HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public Page<FlashcardCategoryDto> getFlashcardCategories(Pageable pageable) {
        Page<FlashcardCategoryEntity> flashcardCategoryEntities = flashcardCategoryService.findAll(pageable);

        return flashcardCategoryEntities.map(mapper::mapTo);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<FlashcardCategoryDto> getFlashcardCategoryById(@PathVariable("id") Long flashcardCategoryId) {
        Optional<FlashcardCategoryEntity> foundFlashcardCategoryEntity = flashcardCategoryService
                .findById(flashcardCategoryId);

        return foundFlashcardCategoryEntity.map(flashcardCategoryEntity -> {
            FlashcardCategoryDto flashcardCategoryDto = mapper.mapTo(flashcardCategoryEntity);

            return new ResponseEntity<>(flashcardCategoryDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteFlashcardCategoryById(@PathVariable("id") Long flashcardCategoryId) {
        flashcardCategoryService.deleteById(flashcardCategoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
