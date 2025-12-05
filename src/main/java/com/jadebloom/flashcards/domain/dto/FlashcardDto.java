package com.jadebloom.flashcards.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashcardDto {

    private Long id;

    @JsonProperty("flashcard_category_id")
    private Long flashcardCategoryId;

    @JsonProperty("front_text")
    private String frontText;

    @JsonProperty("back_text")
    private String backText;

    public FlashcardDto() {
    }

    public FlashcardDto(Long flashcardCategoryId, String frontText, String backText) {
        this.flashcardCategoryId = flashcardCategoryId;

        this.frontText = frontText;

        this.backText = backText;
    }

    public Long getId() {
        return id;
    }

    public Long getFlashcardCategoryId() {
        return flashcardCategoryId;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlashcardCategoryId(Long flashcardCategoryId) {
        this.flashcardCategoryId = flashcardCategoryId;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    @Override
    public String toString() {
        return String.format(
                "FlashcardDto(id=%d, flashcardCategoryId=%d, frontText=%s, backText=%s",
                id, flashcardCategoryId, frontText, backText);
    }

}
