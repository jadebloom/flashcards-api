package com.jadebloom.flashcards.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jadebloom.flashcards.domain.dto.FlashcardDto;
import com.jadebloom.flashcards.domain.entity.FlashcardEntity;
import com.jadebloom.flashcards.exception.compile.FlashcardCategoryNotFound;
import com.jadebloom.flashcards.mapper.Mapper;
import com.jadebloom.flashcards.service.FlashcardCategoryService;
import com.jadebloom.flashcards.service.FlashcardService;

@RestController
@RequestMapping("/api/v1")
public class FlashcardController {

    private final Mapper<FlashcardEntity, FlashcardDto> mapper;

    private final FlashcardService flashcardService;

    private final FlashcardCategoryService flashcardCategoryService;

    public FlashcardController(
            @Qualifier("flashcardMapperImpl") Mapper<FlashcardEntity, FlashcardDto> mapper,
            @Qualifier("flashcardServiceImpl") FlashcardService flashcardService,
            @Qualifier("flashcardCategoryServiceImpl") FlashcardCategoryService flashcardCategoryService) {
        this.mapper = mapper;

        this.flashcardService = flashcardService;

        this.flashcardCategoryService = flashcardCategoryService;
    }

    @PostMapping("/flashcards")
    public ResponseEntity<FlashcardDto> createFlashcard(@RequestBody FlashcardDto flashcardDto)
            throws FlashcardCategoryNotFound {
        System.out.println(flashcardDto);

        Long flashcardCategoryId = flashcardDto.getFlashcardCategoryId();

        if (!flashcardCategoryService.existsById(flashcardCategoryId)) {
            throw new FlashcardCategoryNotFound("Flashcard category with such ID wasn't found");
        }

        FlashcardEntity flashcardEntity = flashcardService.save(mapper.mapFrom(flashcardDto));

        return new ResponseEntity<FlashcardDto>(mapper.mapTo(flashcardEntity), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FlashcardCategoryNotFound.class)
    private ResponseEntity<String> handleFlashcardCategoryNotFoundException() {
        return new ResponseEntity<String>("123", HttpStatus.BAD_REQUEST);
    }

}
