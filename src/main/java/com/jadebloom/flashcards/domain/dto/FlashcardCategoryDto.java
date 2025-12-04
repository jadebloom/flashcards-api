package com.jadebloom.flashcards.domain.dto;

public class FlashcardCategoryDto {

    private Long id;

    private String name;

    public FlashcardCategoryDto() {
    }

    public FlashcardCategoryDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
